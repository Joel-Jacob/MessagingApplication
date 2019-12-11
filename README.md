# MessagingApplication

Project: Messanger Date: 12/10/2019

Assignment Instructions: Make an application which hosts a communication between two activities.
Both activities have an EditText and buttons used to pass the messages and a textview to display the conversation.
The text should be saved to sharedpreferences and should be made available when the application is restarted.
- Use styles to make your application presentable
- do not use raw values, all values(Strings, dimens) should be extracted
- use Glide to display a "user icon" in both activities

Functionality: User enter text in the edit text field and then hits the enter button. The activity switches to the second user, who can do the same.
Implementation: Used Intents to save the data between the two activities. Used shared prefrances to save the text when the app is closed. Utilized Glide to implement user icons.
