# SkypeCommandProject

SkypeCommandProject is a Java application that integrates with Skype to enhance its functionality by adding custom commands. This project represents the initial version of what eventually evolved into **The Armory**, a comprehensive bot framework.

## Table of Contents

- [About](#about)
- [Features](#features)
- [Installation](#installation)
  - [Prerequisites](#prerequisites)
  - [Setup](#setup)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## About

Developed as an early exploration into bot development, SkypeCommandProject aimed to extend Skype's capabilities by introducing custom command functionalities. This foundational work laid the groundwork for the more advanced features present in later iterations, culminating in **The Armory**.

## Features

- **Custom Commands**: Introduce new commands to automate tasks within Skype chats.
- **Integration**: Seamlessly integrates with Skype to enhance user interactions.
- **Extensibility**: Designed to allow the addition of new commands as needed.

## Installation

### Prerequisites

- **Java Development Kit (JDK)**: Ensure JDK 8 or higher is installed on your system.
- **Apache Ant**: Required for building the project.
- **Skype Desktop Application**: The application interacts with the Skype desktop client.

### Setup

1. **Clone the Repository**:

   ```bash
   git clone https://github.com/Horeak/SkypeCommandProject.git
   cd SkypeCommandProject
   ```

2. **Build the Project**:

   Use Apache Ant to build the project:

   ```bash
   ant
   ```

   This will compile the source code and generate the necessary binaries.

3. **Configure Skype**:

   Ensure that the Skype desktop application is installed and running. The application may require specific configurations to allow external applications to interact with it.

## Usage

After building the project:

1. **Run the Application**:

   ```bash
   java -jar dist/SkypeCommandProject.jar
   ```

2. **Interact with Skype**:

   Use the custom commands introduced by the application within your Skype chats to automate tasks and enhance functionality.

## Contributing

As this repository is archived and read-only, contributions are no longer accepted. However, feel free to fork the repository for personal exploration and learning.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
