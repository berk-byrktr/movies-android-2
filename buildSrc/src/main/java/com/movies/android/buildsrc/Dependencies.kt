object Dependencies {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"
    val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    val daggerAndroid = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    val daggerProcessor = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"
    val okhttp = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"
    val glide = "com.github.bumptech.glide:glide:${Versions.glideVersion}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glideVersion}"
    val material = "com.google.android.material:material:${Versions.materialVersion}"
    val androidxCore = "androidx.core:core-ktx:1.3.2"
    val androidFragment = "androidx.fragment:fragment-ktx:${Versions.androidFragmentVersion}"
    val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    val paging = "androidx.paging:paging-runtime-ktx:${Versions.pagingVersion}"
    val swipeToRefresh =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeToRefreshVersion}"
    val junit = "junit:junit:${Versions.junitVersion}"
    val androidxTestRunner = "androidx.test:runner:${Versions.androidxTestRunnerVersion}"
    val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCoreVersion}"
    val truthExt = "androidx.test.ext:truth:${Versions.truthExtVersion}"
    val mockK = "io.mockk:mockk:${Versions.mockKVersion}"
    val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTestingVersion}"
    val coroutinesTesting =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTestingVersion}"
}