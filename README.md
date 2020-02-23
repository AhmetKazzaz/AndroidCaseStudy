# Todo App 
**This is a case study for Hepsiburada**
This sample project utilizes the MVVM architecture as a presentational layer design pattern, 
uses Google's newest packaging system androidx and their biggest recent update to the Android framework 
Android Jetpack library. In this sample I used architecture components such as Pagination, liveData. 
it also utilizes RxJava with retrofit for easier maintainable multithreading when it comes to networking. 
This project can be better refactored and the libraries used can be used more efficiently also the UI is very primary 
but due to lack of  time and since this is only a brief showcase of my capabilities. I suppose this is enough. 
### Structure

**The app has the following packages:**

* `model:` package for response types.
* `ui:` package holds ui related classes such as adapter.
* `util:` has one class that does custom binding
* `view:` holds the activity and viewModel
* `datasource:` responsible for fetching and paginating remote data
* `api:` package holds RESTFUL service related classes like retrofit instance.


### Library References:
1. RxJava2/RxAndroid: [https://github.com/ReactiveX/RxAndroid]
2. Apache Commons: [https://commons.apache.org/] for HashCoding
3. Uses Rx, Databinding, Lifecycle, Pagination
4. Retrofit: [https://github.com/square/retrofit]
5. Picasso Image Loading: [https://github.com/square/picasso]

### License

MIT License

Copyright (c) [2019]

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
