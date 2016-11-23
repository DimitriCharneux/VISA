export LD_LIBRARY_PATH=dependencies/thirdparty/vtk-5.8.0-linux64b:/usr/java/default/jre/lib/amd64/xawt:$LD_LIBRARY_PATH
java -Xmx512M -splash:resources/images/splashscreen_2_4.png -Dpython.cachedir.skip=false -Dpython.cachedir=./cachedir -cp lib/*:dependencies/main/*:plugins/* com.artenum.cassandra.launcher.Run ./resources/cassandra.properties 
