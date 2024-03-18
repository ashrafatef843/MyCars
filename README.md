# What is the My Cars App?
It's a code sample for cars management app that displays your cars and enables you to manage them. The code written in MVI architecture pattern with clean architecture.

# User Stories
### User Story 1: Implementing Vehicle Creation Flow

Objective: Create an intuitive Create Vehicle screen, guiding users through the following streamlined process to add a new vehicle:
Functionality:
- Selection steps: Implement the following selection steps for the user to create a new vehicle: Brand Selection: Choose from available brands.
- Series Selection: Select a series within the chosen brand.
- Build Year: Select the vehicle's build year.
- Fuel Type: Select a fuel type (Diesel, Gasoline, Hybrid, Electric, or other).
- Search: Each selection step will show a list of available options. A search bar should also be included to allow users to filter these options for quicker and easier selection.

### User Story 2: Vehicle List and Management Interface
Objective: Develop a vehicle list and management screen that efficiently displays and manages vehicles the user adds. Integrate the screen with the app’s storage system to ensure that the list of created vehicles and the selection state are preserved across app sessions.

Functionality:
- List Display: Implement a feature to display a comprehensive list of vehicles that the user has added to the app. Each vehicle should be listed with the following details:
- Title: A combination of the brand name and vehicle series, separated by a hyphen (e.g., BMW - 3 Series).
- Subtitle: A combination of the build year and fuel type, separated by a hyphen (e.g., 2018 - Diesel).
- Vehicle Selection: Enable users to select any vehicle from the list as their current choice, highlighting the selected vehicle for easy identification.
- Delete Function: Introduce a delete option for each vehicle, with a restriction that prevents deletion of the currently selected vehicle.

### User Story 3: Dashboard Implementation
Objective: Develop the Dashboard screen, which adapts to user interactions based on the vehicles they have added.

Functionality:
- If no vehicles exist, Display an Add Vehicle button on the Dashboard screen.
- If vehicles exist: Show details of the currently selected vehicle
- Title: Brand - Series
- Subtitle: Year - Fuel Type
- Image: use the default car image from Zeplin and provide a navigation to the 'List Vehicles' screen.
- Features List: Dynamically display a list of features available for the selected vehicle.


# Architecture Pattern
MVI is the architecture pattern of the app.

# Libraries
* Coroutines
* Compose
* Room
* ViewModel
* Hilt
* Kotlin

