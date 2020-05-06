
# react-native-webview-daoupay

## Getting started

`$ npm install react-native-webview-daoupay --save`

### Mostly automatic installation

`$ react-native link react-native-webview-daoupay`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-webview-daoupay` and add `RNWebviewDaoupay.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNWebviewDaoupay.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNWebviewDaoupayPackage;` to the imports at the top of the file
  - Add `new RNWebviewDaoupayPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-webview-daoupay'
  	project(':react-native-webview-daoupay').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-webview-daoupay/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-webview-daoupay')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNWebviewDaoupay.sln` in `node_modules/react-native-webview-daoupay/windows/RNWebviewDaoupay.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Webview.Daoupay.RNWebviewDaoupay;` to the usings at the top of the file
  - Add `new RNWebviewDaoupayPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNWebviewDaoupay from 'react-native-webview-daoupay';

// TODO: What to do with the module?
RNWebviewDaoupay;
```
  