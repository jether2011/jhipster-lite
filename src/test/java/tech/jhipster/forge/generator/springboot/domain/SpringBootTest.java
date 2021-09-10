package tech.jhipster.forge.generator.springboot.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import tech.jhipster.forge.UnitTest;

@UnitTest
class SpringBootTest {

  @Test
  void shouldGetVersion() {
    assertThat(SpringBoot.getVersion()).isEqualTo(SpringBoot.SPRING_BOOT_VERSION);
  }
}
