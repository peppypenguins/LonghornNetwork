# LonghornNetwork
## ECE 422C Lab 5: Networking with Fellow Longhorns!

### Longhorn Network

### Overview
This project simulates a social network called **Longhorn Network**, where students are matched with roommates, assigned to pods, and find referral paths for internships.

### Key Objectives
1. Implement the **Gale-Shapley** algorithm for roommate assignment.
2. Form pods using **Prim’s algorithm** based on connection strengths.
3. Find internship referral paths using **Dijkstra’s algorithm**.
4. Use **multithreading** to simulate real-time actions like friend requests and chatting.

---

### Getting Started

#### Folder Structure
- `src/`: Contains the main code files.
- `testing/`: Contains `input_sample.txt` and `output_sample.txt`: Sample input and output file format.
- `README.md`: Project instructions.

#### Prerequisites
- **Java**
- Basic knowledge of graph algorithms, threading, and file I/O.
- If you are unfamiliar with Gale-Shapley, you will find [these resources] (https://www.sanfoundry.com/java-program-gale-shapley-algorithm/) helpful. 
---

### Instructions

#### Step 1: Setting Up
1. **Fork** this repository to start working on your own copy.
2. **Clone** the repository to your local machine:
   ```bash
   git clone https://github.com/ayushroychowdhury/LonghornNetwork.git
3. **Create a branch with your name**
   ```bash
   git checkout -b your-branch-name
4. **Choose what you want to add and commit**
   ```bash
   git add <filepath/filename>
   git commit -m "commit message of your choosing"
6. **Push to your branch**, DO NOT COMMIT OR PUSH TO MAIN
   ```bash
   git status ##make sure that your branch name pops up here
   git push

7. If this does not make sense, please check announcement walking you through the process with a simple video. 
---

### Step 2: Generate UML Diagram, State Diagram and Javadoc, DUE DATE: Nov 20th, 2024 11:59pm

- **Generate UML Diagram and State Diagram**:
  - Create a UML diagram based on the class and method signatures.
  - Include core relationships between classes, such as inheritance, aggregation, and associations.
  - For the state diagram show the behavior of Longhorn Network, and how each interaction from the core components will occur.
  
- **Write Javadoc**:
  - Generate Javadoc comments for each class and method based on the provided signatures.
  - Include descriptions of parameters and return types.
---

### Step 3: Implement Core Components

Follow the steps below to implement the core functionality of the Longhorn Network. Each section provides specific details, common edge cases, and additional clarifications to help you complete the assignment.

#### 1. Data Parsing (`DataParser.java`)
- **Task**: Implement the `parseStudents` method to read student information from a file and create `UniversityStudent` objects.
- **Details**:
  - Input file format is provided in `input_sample.txt`.
  - Parse all attributes (e.g., name, age, gender, major, GPA).
  - Store `roommatePreferences` and `previousInternships` as lists.
  - Validate input and handle missing or invalid data gracefully.

- **Edge Cases**:
  - Missing fields (e.g., no roommate preferences).
  - Incorrect formatting in input file (e.g., missing `:` separator).

---

#### 2. Roommate Matching (`GaleShapley.java`)
- **Task**: Implement the Gale-Shapley stable matching algorithm to pair students based on roommate preferences.
- **Details**:
  - Each student has a list of preferred roommates. Mutual preferences are prioritized.
  - Students without preferences should remain unpaired.
  - Handle cases where preferences are incomplete or cyclic.

- **Steps**:
  - Parse preference lists for all students.
  - Use a queue for unpaired students to iterate through proposals.
  - A proposal is accepted if the receiver is unpaired or prefers the proposer over their current roommate.

- **Edge Cases**:
  - Students with empty or partial preference lists.
  - Cyclic or conflicting preferences (e.g., Alice prefers Bob, Bob prefers Charlie, Charlie prefers Alice).

---

#### 3. Pod Formation (`PodFormation.java`)
- **Task**: Use Prim’s algorithm to form pods (groups of students) by minimizing connection strengths.
- **Details**:
  - **Number of Pods**: Divide the students into pods of size ≤ `podSize`.
  - **Disconnected Graphs**: Assume the input graph can have disconnected components. Each component should form separate pods.
  - Use connection strength (see **Connection Strength** below) to build the weighted graph.

- **Steps**:
  - Create an adjacency list or matrix representation of the graph.
  - Apply Prim’s algorithm to construct a Minimum Spanning Tree (MST) for each connected component.
  - Divide the MST into pods of the specified size (`podSize`).

- **Edge Cases**:
  - Single-node components (a single student forms their own pod).
  - Graphs with varying numbers of students or incomplete connections.

---

#### 4. Referral Path Finding (`ReferralPathFinder.java`)
- **Task**: Use Dijkstra’s algorithm to find the shortest path (strongest connection) to a student who interned at a specific company.
- **Details**:
  - Stronger connections should be treated as "shorter" paths.
  - Allow user input to specify the target company.

- **Steps**:
  - Invert the connection weights (e.g., use `10 - weight` for stronger connections).
  - Traverse the graph using Dijkstra’s algorithm from the starting student.
  - Return the path if a student with the target internship is found.

- **Edge Cases**:
  - No student with the target internship.
  - Disconnected graphs with no path to a target.

---

#### 5. Connection Strength (`calculateConnectionStrength`)
- **Task**: Implement a formula to calculate the connection strength between two students based on the following criteria:
  - **Roommate**: Add 5 if they are roommates.
  - **Pod Member**: Add 4 if they are in the same pod.
  - **Shared Internships**: Add 3 for each shared internship.
  - **Chat History**: Add 2 if they have interacted.
  - **Same Major**: Add 1 if they share the same major.

- **Details**:
  - This method will be implemented in the `UniversityStudent` class as an override of the abstract method in `Student`.
  - Ensure the method accurately accounts for all the above factors to return the correct connection strength.

- **Edge Cases**:
  - Students with no shared attributes (e.g., no roommate, no shared internships, etc.).
  - Students with no chat history or no defined pod membership.
  - Students who are roommates but have no other connections.

---

#### 6. Multithreading (`FriendRequestThread` and `ChatThread`)
- **Task**: Simulate concurrent actions like sending friend requests and chatting between students.
- **Details**:
  - Use threads to manage these interactions concurrently, simulating real-time behavior.
  - Ensure thread-safe operations when updating shared resources such as `chatHistory`.

- **Steps**:
  1. Use `ExecutorService` to manage multiple threads efficiently.
  2. Implement thread-safe methods using synchronized blocks or concurrent data structures to handle:
     - Friend requests: Add students to each other’s friend lists.
     - Messaging: Update chat histories between students.
  3. Log interactions (e.g., "Alice sent a friend request to Bob") for debugging and verification.

- **Testing**:
  - Test with overlapping friend requests and chat threads to ensure thread safety and proper synchronization.
  - Verify that all friend requests and messages are processed without data corruption or missed updates.

---

#### 7. Testing and Validation
- **Sample Input**:
  - Use the provided `input_sample.txt` to verify your implementation.
  - Ensure all attributes in the input file (e.g., roommate preferences, internships) are parsed correctly.

- **Expected Output**:
  - Roommate Assignments:
    - Each student should be matched with their highest-priority roommate if mutually possible.
  - Pods:
    - Pods should have no more than the specified `podSize`.
    - Each pod should consist of students with strong connection strengths.
  - Referral Paths:
    - The path to a student with the specified internship should have the shortest total connection cost.

- **Edge Cases**:
  1. **Incomplete Data**:
     - Students with missing roommate preferences, no internships, or incomplete attributes.
  2. **Disconnected Graphs**:
     - Ensure that all connected components are handled independently.
  3. **Isolated Nodes**:
     - A single student with no connections should form their own pod.
  4. **Multithreading**:
     - Overlapping threads for friend requests and messaging must not corrupt shared resources.
     - Ensure all threads finish execution within a reasonable time frame.

- **Validation**:
  - Compare the output of your implementation with the provided `output_sample.txt`.
  - Write your own additional test cases to ensure robustness and correctness.


---

