# **DGL-114 Assignment 1**

---

## **Author:** Reeve Jarvis

---

> Java Class Files Included:
>
> - Button.java
> - ColorMatchGame.java
> - ColorUtilities.java
> - MainActivity.java

Color Match:

As per the assignment brief, this application is adapted from the code presented throughout chapters 1-3 of the textbook to produce a Color Matching Game. It also demonstrates the use of MVC architecture to provide a separation of concerns.

- Button.java contains the model data for each button, tracking their color values and selected state.

- ColorMatchGame.java contains the game logic of the application, with a series of methods used to execute and maintain gameplay. This java class communcates the gameplay mechanics as well as the button model data to the controller.

- ColorUtilities.java contains the necessary logic to blend color values and produce a new result.

- Mainactivity.java represents the controller, access model data and adjusting the view as necessary.

The applications main purpose is for the user to participate in a color matching game using randomly generated color values presented in button form. In order to achieve this the application leverages the color class of the Android.graphics package.

The app interface is presented in a way similar to the LightsOut Game from our textbook work. The app does have a landcape presentation though I was unable to implement the savedInstance state properly, so each time it is rotated the game begins fresh. Adjustments include the addition of a target color indicator, and I have also taken the liberty to include a win counter to track the total number of won games throughout each game instance. All resource values are handled within their own respective XML documents, though I kept getting unwanted changes when I was adjusting their locations in the Design window.

This assignment presented many challenges but I am quite pleased with the end results.
