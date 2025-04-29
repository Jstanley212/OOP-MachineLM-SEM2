# OOP-MachineLM-SEM2

Overview of Project:
This Java project implements a Naive Bayes classifier in order to predict is a vehicle will a have a violation based of four categorical features, These features are:
1. Age Group (Young/Adult)
2. Vehicle Type (Car/Truck)
3. Prior Violations (Yes/No)
4. Maintenance Record (Good/Poor)

The project has four levels of functionality, all building on the last.

Frequency Table of Data:

<img width="429" alt="Image" src="https://github.com/user-attachments/assets/0ee8c9e7-de41-4e17-848c-17769c236e0a" />


Project Structure:

Code Folder:

Control.java
- Entry point of the application
- Initializes and launches the GUI
- Manages the main flow of the project

Data.java
- Represents a single data record
- Stores all the feature values and the violation status
- Provides the methods for the data manipulation

DataLoader.java
- Is responsible for handling of reading data from CSV file
- Loads and stores training data
- Provides the methods for data access and manipulation

NaiveBayes.java
- Where main model code is
- Implements the Naive Bayes classification algorithm
- Calculates probabilities from training data
- Predictions based on input features
- Dynamic probability calculation
- Handles categorical data
- Provides prediction probabilities

InputPanel.java
- GUI component for user input for predictions
- Has dropdown menus for all features
- Allows users to make predictions
- Displays prediction results with probabilities using dialog boxes

TrainingDataPanel.java
- GUI component for adding new training data
- Allows users to input feature values and labels
- Updates the model with new data
- Recalculates classifier rules

ModelEvaluation.java
- Evaluates model performance
- Implements stratified data
- Calculates accuracy of probabilities
- Displays prediction results of test data

MainFrame.java
- Main application window of GUI
- Contains three tabs:
- Predict: Make predictions using current model
- Add Data: Add new training data
- Model Evaluation: Evaluate model performance



Levels 1-4:

Level 1: Basic Predictions
- GUI interface for input
- Hardcoded predictive rules
- Shows prediction results

Level 2: Dynamic Training
- Reads data from CSV file
- Calculates probabilities dynamically
- Trains classifier on dataset
- Updates rules automatically

Level 3: Training
- GUI for adding new training data
- Updates model with new data
- Recalculates probabilities
- Maintains data consistency

Level 4: Testing the Model
- Stratified data (150 training, 50 test)
- Automatic testing
- Shows prediction accuracy
- Displays individual test results

Running the project
1. Have Java is installed on your system
2. Compile all Java files in the Code directory
3. Run the Control class from Control.java class

What I would add:
-If I had more time to do this project I would like to improve the look of the GUI, so the results are displayed better to the user.
-I would also of liked to add the option to add multiple rows of data to the set instead of one at a time.
