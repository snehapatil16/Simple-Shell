## A Simple Shell (File System Tree)

### Shell Operations

- **cd <directory>**:
  - Change directory to the specified directory.
- **ls**:
  - List all files and folders in the current directory.
- **touch <filename>**:
  - Create a new empty file with the given name.
- **mkdir <directory name>**:
  - Create a new empty directory with the given name.
- **pwd**:
  - Print the path to the current directory, starting with the root.
- **rm <filename>**:
  - Remove a file.
- **rmdir <directory name>**:
  - Remove an EMPTY directory.
- **tree**:
  - Pretty-print the contents of the directory recursively.

### Implementation Details

#### Filesystem Class

- **Node (inner class)**:
  - Implements Serializable.
  - Represents files and directories in the filesystem.
  - Contains instance variables: name, children, parent, isDirectory.
  - Provides methods: isDirectory(), children(), appendChild(), isRoot().
  
- **FileSystem**:
  - Implements Serializable.
  - Represents the filesystem using a tree structure.
  - Contains instance variables: root, currentDirectory.
  - Provides methods: checkMakeFile(), ls(), mkdir(), touch(), cd(), rm(), rmdir(), tree(), pwd().
  
#### Driver Class

- **Driver**:
  - Contains main method to interact with the shell.
  - Creates an instance of FileSystem and Scanner.
  - Listens for user commands and calls corresponding FileSystem methods.
  - Catches exceptions and provides feedback to the user.
  - Saves FileSystem to a file named fs.data using ObjectOutputStream.

### Serialization

- Both Filesystem and Node classes implement Serializable.
- Allows saving and loading filesystem data to/from a file (fs.data) using ObjectInputStream and ObjectOutputStream.
- Ensures data retention between program runs.
