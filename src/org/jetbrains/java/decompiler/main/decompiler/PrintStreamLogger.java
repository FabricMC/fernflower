// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package org.jetbrains.java.decompiler.main.decompiler;

import org.jetbrains.java.decompiler.main.extern.IFernflowerLogger;
import org.jetbrains.java.decompiler.util.TextUtil;

import java.io.PrintStream;

public class PrintStreamLogger extends IFernflowerLogger {

  private final PrintStream stream;
  private int indent;

  public PrintStreamLogger(PrintStream printStream) {
    stream = printStream;
    indent = 0;
  }

  @Override
  public void writeMessage(String message, Severity severity) {
    if (accepts(severity)) {
      stream.println(severity.prefix + TextUtil.getIndentString(indent) + message);
    }
  }

  @Override
  public void writeMessage(String message, Severity severity, Throwable t) {
    if (accepts(severity)) {
      writeMessage(message, severity);
      t.printStackTrace(stream);
    }
  }

  @Override
  public void startReadingClass(String className) {
    if (accepts(Severity.INFO)) {
      writeMessage("Decompiling class " + className, Severity.INFO);
      ++indent;
    }
  }

  @Override
  public void endReadingClass(String className) {
    if (accepts(Severity.INFO)) {
      --indent;
      writeMessage("Decompiled  class " + className, Severity.INFO);
    }
  }

  @Override
  public void startClass(String className) {
    if (accepts(Severity.INFO)) {
      writeMessage("Processing class " + className, Severity.TRACE);
      ++indent;
    }
  }

  @Override
  public void endClass(String className) {
    if (accepts(Severity.INFO)) {
      --indent;
      writeMessage("Processed  class " + className, Severity.TRACE);
    }
  }

  @Override
  public void startMethod(String methodName) {
    if (accepts(Severity.INFO)) {
      writeMessage("Processing method " + methodName, Severity.TRACE);
      ++indent;
    }
  }

  @Override
  public void endMethod(String methodName) {
    if (accepts(Severity.INFO)) {
      --indent;
      writeMessage("Processed  method  " + methodName, Severity.TRACE);
    }
  }

  @Override
  public void startWriteClass(String className) {
    if (accepts(Severity.INFO)) {
      writeMessage("Writing class " + className, Severity.TRACE);
      ++indent;
    }
  }

  @Override
  public void endWriteClass(String className) {
    if (accepts(Severity.INFO)) {
      --indent;
      writeMessage("written class " + className, Severity.TRACE);
    }
  }

  @Override
  public IFernflowerLogger clone() {
    return new PrintStreamLogger(stream);
  }
}
