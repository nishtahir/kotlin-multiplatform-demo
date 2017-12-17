# Kotlin multiplatform React Demo

# Usage

Build the backend server application 

```
./gradlew backend:installDist
```

Start the backend server by running

```
./backend/build/install/backend/bin/backend
```

Next build the frontend client by running

```
./gradlew front-end:build
```

Start the frontend webpack server by running

```
./gradlew front-end:run
```

You can now visit the front end in your browser by pointing it at `http://localhost:8088/`

To stop the webpack server 

```
./gradlew front-end:stop
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