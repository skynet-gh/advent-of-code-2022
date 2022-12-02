# Advent of Code 2022

Requires [Clojure](https://clojure.org/guides/install_clojure), and [GraalVM](https://github.com/graalvm/graalvm-ce-builds/releases) with `native-image`.

## Build

```
clj -T:build uber
native-image -jar target/advent-of-code-2022.jar --no-fallback --report-unsupported-elements-at-runtime
```

## Config

```
java -agentlib:native-image-agent=config-merge-dir=native-res/META-INF/native-image/advent-of-code-2022/java -jar target/advent-of-code-2022.jar
```
