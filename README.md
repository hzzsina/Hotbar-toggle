# Hotbar Toggle

A tiny client-side Fabric mod for Minecraft Java Edition 26.2.

## Behaviour

- Press `1` while hotbar slot 1 is selected to select slot 2.
- Press `1` while hotbar slot 2 is selected to select slot 1.
- Pressing `1` from any other hotbar slot keeps Minecraft's normal behaviour and selects slot 1.
- Holding the key counts as one press; it does not toggle repeatedly.
- The items are not moved or swapped.

## Installation

1. Install Minecraft Java Edition 26.2 and Fabric Loader 0.19.3 or newer.
2. Install Fabric API for Minecraft 26.2.
3. Put `hotbar-toggle-1.0.0.jar` in the Minecraft `mods` folder.

## Building from source

Install JDK 25, then run `./gradlew build` (`gradlew.bat build` on Windows).
The finished mod will be in `build/libs`.

## License

CC0-1.0.
