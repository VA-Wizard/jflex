package jflex.ucd_generator.emitter.unicode_version;

import static com.google.common.truth.Truth.assertThat;

import com.google.common.io.Files;
import java.io.File;
import java.nio.charset.StandardCharsets;
import jflex.testing.diff.DiffOutputStream;
import jflex.ucd_generator.ucd.UcdFileType;
import jflex.ucd_generator.ucd.UcdVersion;
import org.junit.Test;

/** Test for {@link UnicodeVersionEmitter}. */
public class UnicodeVersionEmitterTest {
  @Test
  public void emitUnicode_0_1() throws Exception {
    File goldenFile =
        new File("javatests/jflex/ucd_generator/emitter/unicode_version/Unicode_0_1.java.golden");
    // in-memory output
    DiffOutputStream output =
        new DiffOutputStream(Files.newReader(goldenFile, StandardCharsets.UTF_8));

    // fake ucd version 0.1
    UcdVersion ucd0_1 =
        UcdVersion.builder()
            .withVersion("0.1")
            .putFile(UcdFileType.UnicodeData, new File("FakeUnicodeData.txt"))
            .build();

    UnicodeVersionEmitter emitter = new UnicodeVersionEmitter("org.example", ucd0_1);

    emitter.emitUnicodeVersion(output);
    assertThat(output.isCompleted()).isTrue();
  }
}
