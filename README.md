
# Project description:

Weathery is an android application that allows user to check the current weather status at user’s current location by using Open Weather Map api. It also provides the forecast information in next 16 days. Furthermore, Weathery enables user to see the weather state in most of the cities all over the world.

# Feature description:
## Main screen:
For the first time when user opens application, there is a permission request popup will be displayed to ask for GPS access. User needs to allow this permission, so the app can get user’s current information and displays weather information for his/her city.
If he/she doesn’t accept to access to his/her location, application will display the weather information of default location which is Helsinki.
The background of the app will be updated based on current time and sunrise, sunset times. 
Similar to the background, the weather icon is not only displayed based on weather status but also the current time and sunrise, sunset times.

![image](https://user-images.githubusercontent.com/1129823/47901101-af6e6900-de87-11e8-8861-7b67fba32a50.png)

## Forecast screen:
Since to get this forecast information requires a paid account, sometime this screen won’t be able to display the info. Weathery is using a shared paid API Key for getting the data. So, there shouldn’t be any problem.

![image](https://user-images.githubusercontent.com/1129823/47901107-b6957700-de87-11e8-9a02-2be67828f288.png)

## City bookmark screen
To check the weather information of a city, user needs to input the name of city into the text field and click add button. User won’t be able to add same city. And if the city is not exist the app will show an alert message box with error message.

![image](https://user-images.githubusercontent.com/1129823/47901111-bac19480-de87-11e8-80bf-899d830c521d.png)

# Project implementation:
The app is developed with Architecture Components with Live Data, View Model and UI components. Live Data components use Retrofit 2 library to manage API request and get data to return back to View Model. In the UI components, the callback implementation will be added to handle the data response and update the UI.

![image](https://user-images.githubusercontent.com/1129823/47901124-c01edf00-de87-11e8-8f13-75005aee475d.png)
