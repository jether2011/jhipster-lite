package tech.jhipster.lite.generator.module.infrastructure.secondary;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

enum MavenScope {
  COMPILE,
  PROVIDED,
  SYSTEM,
  RUNTIME,
  TEST;

  private static final Map<String, MavenScope> SCOPES = buildScopes();

  private static Map<String, MavenScope> buildScopes() {
    return Stream.of(values()).collect(Collectors.toUnmodifiableMap(scope -> scope.name().toLowerCase(), Function.identity()));
  }

  static MavenScope from(String scope) {
    if (scope == null) {
      return null;
    }

    return SCOPES.get(scope.toLowerCase());
  }

  String key() {
    return name().toLowerCase();
  }
}
