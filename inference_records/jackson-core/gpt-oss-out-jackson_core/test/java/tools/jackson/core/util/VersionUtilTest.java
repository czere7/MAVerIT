package tools.jackson.core.util;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.*;
import java.net.URI;
import java.util.*;
import java.lang.reflect.Field;
import javax.tools.*;

import tools.jackson.core.Version;

/**
 * JUnit 4 tests for {@link VersionUtil}.
 */
public class VersionUtilTest {

    /* ---------- helper methods for reflection ---------- */

    private void assertVersionFields(Version v,
                                     int major, int minor, int patch,
                                     String snapshot, String groupId, String artifactId)
            throws Exception {
        Class<?> cls = Version.class;
        Field f;

        f = cls.getDeclaredField("_majorVersion");
        f.setAccessible(true);
        assertEquals(major, f.getInt(v));

        f = cls.getDeclaredField("_minorVersion");
        f.setAccessible(true);
        assertEquals(minor, f.getInt(v));

        f = cls.getDeclaredField("_patchLevel");
        f.setAccessible(true);
        assertEquals(patch, f.getInt(v));

        f = cls.getDeclaredField("_snapshotInfo");
        f.setAccessible(true);
        assertEquals(snapshot == null ? "" : snapshot, f.get(v).toString());

        f = cls.getDeclaredField("_groupId");
        f.setAccessible(true);
        assertEquals(groupId == null ? "" : groupId, f.get(v).toString());

        f = cls.getDeclaredField("_artifactId");
        f.setAccessible(true);
        assertEquals(artifactId == null ? "" : artifactId, f.get(v).toString());
    }

    private void assertUnknownVersion(Version v) throws Exception {
        assertVersionFields(v, 0, 0, 0, "", "", "");
    }

    /* ---------- in‑memory compilation utilities ---------- */

    /**
     * Represents a Java source file stored as string.
     */
    private static class SourceJavaFileObject extends SimpleJavaFileObject {
        final String src;

        SourceJavaFileObject(String name, String src) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.src = src;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return src;
        }
    }

    /**
     * Holds the compiled byte code in memory.
     */
    private static class CompiledJavaFileObject extends SimpleJavaFileObject {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        CompiledJavaFileObject(String name, Kind kind) {
            super(URI.create("string:///" + name.replace('.', '/') + kind.extension), kind);
        }

        @Override
        public OutputStream openOutputStream() throws IOException {
            return baos;
        }

        byte[] getBytes() {
            return baos.toByteArray();
        }
    }

    /**
     * JavaFileManager that stores compiled classes in a map.
     */
    private static class ClassFileManager extends ForwardingJavaFileManager<StandardJavaFileManager> {
        final Map<String, CompiledJavaFileObject> compiled = new HashMap<>();

        protected ClassFileManager(StandardJavaFileManager sjfm) {
            super(sjfm);
        }

        @Override
        public JavaFileObject getJavaFileForOutput(Location location,
                                                   String className,
                                                   JavaFileObject.Kind kind,
                                                   FileObject sibling) throws IOException {
            CompiledJavaFileObject file = new CompiledJavaFileObject(className, kind);
            compiled.put(className, file);
            return file;
        }
    }

    /**
     * Compile provided source code strings and load the resulting classes.
     *
     * @param sources map of fully qualified class name to source string
     * @return map of class name to loaded Class object
     */
    private Map<String, Class<?>> compileAndLoad(Map<String, String> sources) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager stdFm = compiler.getStandardFileManager(null, null, null);
        ClassFileManager fileMgr = new ClassFileManager(stdFm);

        List<JavaFileObject> javaFiles = new ArrayList<>();
        for (Map.Entry<String, String> e : sources.entrySet()) {
            javaFiles.add(new SourceJavaFileObject(e.getKey(), e.getValue()));
        }

        JavaCompiler.CompilationTask task =
                compiler.getTask(null, fileMgr, null, null, null, javaFiles);
        if (!task.call()) {
            throw new RuntimeException("Compilation failed");
        }

        // Load compiled classes using a custom ClassLoader
        Map<String, Class<?>> loaded = new HashMap<>();
        ClassLoader parent = this.getClass().getClassLoader();
        class MemoryClassLoader extends ClassLoader {
            MemoryClassLoader(ClassLoader parent) { super(parent); }
            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                CompiledJavaFileObject file = fileMgr.compiled.get(name);
                if (file == null) {
                    return super.findClass(name);
                }
                byte[] bytes = file.getBytes();
                return defineClass(name, bytes, 0, bytes.length);
            }
        }

        ClassLoader loader = new MemoryClassLoader(parent);
        for (String className : sources.keySet()) {
            loaded.put(className, Class.forName(className, true, loader));
        }
        return loaded;
    }

    /* ---------- tests ---------- */

    @Test
    public void testParseVersionWithVariousSeparators() throws Exception {
        Version v = VersionUtil.parseVersion("1-2-3-SNAPSHOT", "grp", "art");
        assertVersionFields(v, 1, 2, 3, "SNAPSHOT", "grp", "art");

        v = VersionUtil.parseVersion("4_5_6", "g","a");
        assertVersionFields(v, 4, 5, 6, "", "g", "a");

        v = VersionUtil.parseVersion("7/8.9:10;11", "x","y"); // more than 3 parts
        assertVersionFields(v, 7, 8, 9, "10", "x", "y");
    }

    @Test
    public void testParseVersionWithWhitespaceAndNull() throws Exception {
        Version v = VersionUtil.parseVersion("   1.2.0   ", null, null);
        assertVersionFields(v, 1, 2, 0, "", "", "");

        v = VersionUtil.parseVersion(null, "g","a");
        assertUnknownVersion(v);

        v = VersionUtil.parseVersion("", "g","a");
        assertUnknownVersion(v);
    }

    @Test
    public void testParseVersionPart() throws Exception {
        assertEquals(123, VersionUtil.parseVersionPart("123abc"));
        assertEquals(12,  VersionUtil.parseVersionPart("00012"));
        assertEquals(0,  VersionUtil.parseVersionPart(""));
        assertEquals(0,  VersionUtil.parseVersionPart("a12"));
        // parseVersionPart stops before non-digit characters; '-5' yields 0
        assertEquals(0,  VersionUtil.parseVersionPart("-5"));
    }

    @Test
    public void testThrowInternal() {
        try {
            VersionUtil.throwInternal();
            fail("expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Internal error: this code path should never get executed",
                    e.getMessage());
        }
    }

    @Test
    public void testThrowInternalReturnAny() {
        try {
            VersionUtil.throwInternalReturnAny();
            fail("expected IllegalStateException");
        } catch (IllegalStateException e) {
            assertEquals("Internal error: this code path should never get executed",
                    e.getMessage());
        }
    }

    @Test
    public void testVersionForWithMissingPackageVersion() throws Exception {
        // Using a class from java.lang, which has no PackageVersion in its package.
        Version v = VersionUtil.versionFor(String.class);
        assertUnknownVersion(v);
    }

    @Test
    public void testVersionForWithPresentPackageVersion() throws Exception {
        String pkgName = "tools.jackson.core.util";

        Map<String, String> sources = new HashMap<>();
        // Dummy component that will be used as the argument class.
        sources.put(pkgName + ".DummyComponent",
                "package " + pkgName + ";\n" +
                        "public class DummyComponent {}\n");

        // PackageVersion providing a concrete Version.
        sources.put(pkgName + ".PackageVersion",
                "package " + pkgName + ";\n" +
                        "import tools.jackson.core.Versioned;\n" +
                        "import tools.jackson.core.Version;\n" +
                        "\n" +
                        "public class PackageVersion implements Versioned {\n" +
                        "    public PackageVersion() {}\n" +
                        "    @Override\n" +
                        "    public Version version() {\n" +
                        "        return new Version(9, 8, 7, \"SNAP\", \"group\", \"artifact\");\n" +
                        "    }\n" +
                        "}\n");

        Map<String, Class<?>> loaded = compileAndLoad(sources);
        Class<?> dummyCls = loaded.get(pkgName + ".DummyComponent");

        Version v = VersionUtil.versionFor(dummyCls);

        assertVersionFields(v, 9, 8, 7, "SNAP", "group", "artifact");
    }

    /* ---------- additional tests to cover missed branches ---------- */

    @Test
    public void testParseVersionWithOnlyWhitespace() throws Exception {
        // String containing only whitespace should result in unknown version
        Version v = VersionUtil.parseVersion("     ", null, null);
        assertUnknownVersion(v);
    }

    @Test
    public void testParseVersionSinglePart() throws Exception {
        // Only major version part; minor and patch default to 0
        Version v = VersionUtil.parseVersion("42", "grp", "art");
        assertVersionFields(v, 42, 0, 0, "", "grp", "art");
    }

    @Test
    public void testParseVersionTwoParts() throws Exception {
        // Major and minor parts; patch defaults to 0
        Version v = VersionUtil.parseVersion("1_2", "g", "a");
        assertVersionFields(v, 1, 2, 0, "", "g", "a");
    }

    @Test
    public void testVersionForWithFailingPackageVersion() throws Exception {
        String pkgName = "tools.jackson.core.util.testfail";

        Map<String, String> sources = new HashMap<>();
        // Dummy component that will be used as the argument class.
        sources.put(pkgName + ".DummyComponent",
                "package " + pkgName + ";\n" +
                        "public class DummyComponent {}\n");

        // PackageVersion whose constructor throws an exception,
        // causing VersionUtil.versionFor to return unknown version
        sources.put(pkgName + ".PackageVersion",
                "package " + pkgName + ";\n" +
                        "import tools.jackson.core.Versioned;\n" +
                        "import tools.jackson.core.Version;\n" +
                        "\n" +
                        "public class PackageVersion implements Versioned {\n" +
                        "    public PackageVersion() { throw new RuntimeException(\"boom\"); }\n" +
                        "    @Override\n" +
                        "    public Version version() {\n" +
                        "        return new Version(1,0,0,null,null,null);\n" +
                        "    }\n" +
                        "}\n");

        Map<String, Class<?>> loaded = compileAndLoad(sources);
        Class<?> dummyCls = loaded.get(pkgName + ".DummyComponent");

        Version v = VersionUtil.versionFor(dummyCls);

        assertUnknownVersion(v);
    }
}
