# Kotlin multiplatform React Demo

A demo application showcasing building a multiplatform project using kotlin on the backend
and kotlinjs with React Bindings on the frontend as well as the Material Design Lite library.

# Usage

Build the backend server application 

``` sh
./gradlew backend:installDist
```

Start the backend server by running

``` sh
./backend/build/install/backend/bin/backend
```

Next build the frontend client by running

``` sh
./gradlew front-end:build
```

Start the frontend webpack server by running

``` sh
./gradlew front-end:run
```

You can now visit the front end in your browser by pointing it at `http://localhost:8088/`

To stop the webpack server 

``` sh
./gradlew front-end:stop
```

# Known issues

1. At the time of development, there's a known issue in the kotlin wrappers - kotlin extensions library that 
causes it to fail at build time with the message

``` sh
./node_modules/kotlin-extensions/kotlin-extensions.js
Module not found: Error: Can't resolve 'src' in '<path>/front-end/build/node_modules/kotlin-extensions'
 @ ./node_modules/kotlin-extensions/kotlin-extensions.js 20:4-42
 @ ./node_modules/kotlin-react-dom/kotlin-react-dom.js
 @ ./js/front-end.js
 @ multi webpack-dev-server/client?http://localhost:8088/ webpack/hot/dev-server ./front-end
 ```
an issue has been filed for it [here](https://github.com/JetBrains/kotlin-wrappers/issues/30). 
It is set to be fixed in the next release.

A work around is to locate the `kotlin-extensions.js` npm package in the front end build's npm modules folder, and comment
out the following line around line 20.

``` javascript
function foo() {
// require.context('src', true, /\.css$/);
}
```

# License

Copyright 2017 Nish Tahir

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.