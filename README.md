# 🎧 Miksher - Java Audio Mixer and Device Manager

Mixer is a professional desktop application developed in Java, designed for using multiple output devices for shared viewing or listening. You can connect multiple devices (speakers/headphones) and adjust the volume and use of each device individually.

## ✨ Features

*   **Audio Playback Control:** Utilize the `AudioPlayer` core module for precise audio streaming and playback management.
*   **Device Management:** Systematically control and monitor audio hardware devices using the `DeviceManager`.
*   **Intuitive UI:** Built with a dedicated UI layer (`MainFrame`, `DeviceControlPanel`) for ease of use.
*   **Modular Architecture:** Follows a clean MVC-like pattern separating core logic, UI, and business/device management.

## 🚀 Getting Started

### Prerequisites

You must have the Java Development Kit (JDK) installed on your system.

### Build Instructions
To build the project, navigate to the root directory of the repository and run:

```
# Assuming Maven is used, based on pom.xml
mvn clean install

You can also make an installer by wrapping the .jar file with Launch4j and creating the installer using the Inno Setup Compiler (sample code is included in the project)
```
### Install Instructions

To install the project, you can download the installer from Realese.