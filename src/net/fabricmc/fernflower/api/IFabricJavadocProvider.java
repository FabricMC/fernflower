package net.fabricmc.fernflower.api;

import org.jetbrains.java.decompiler.struct.StructClass;
import org.jetbrains.java.decompiler.struct.StructField;
import org.jetbrains.java.decompiler.struct.StructMethod;

import java.util.Map;

/**
 * Provides (optional) javadoc for Classes/Methods/Fields encountered by
 *  {@link org.jetbrains.java.decompiler.main.ClassWriter}.
 *
 * May be set as a property in the constructor of {@link org.jetbrains.java.decompiler.main.Fernflower} by using
 *  the key {@code IFabricJavadocProvider.PROPERTY_NAME}
 */
public interface IFabricJavadocProvider {
  String PROPERTY_NAME = "fabric:javadoc";

  default Iterable<String> getClassDoc(StructClass structClass) {
    return null;
  }

  default Iterable<String> getFieldDoc(StructClass structClass, StructField structField) {
    return null;
  }

  default Iterable<String> getMethodDoc(StructClass structClass, StructMethod structMethod) {
    return null;
  }
}
