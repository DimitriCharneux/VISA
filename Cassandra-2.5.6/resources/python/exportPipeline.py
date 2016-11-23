
print "Start to export the pipeline... "

pipeline  = cassandra.getPipeLineManager()
objList = pipeline.getVtkObjectHashtable()

import com.artenum.cassandra.pipeline.io
from com.artenum.cassandra.pipeline.io import PipelineToJythonExporter
exporter = PipelineToJythonExporter()
exporter.setSelectedVtkObjectList( objList )

print "exporter defined"

exporter.setOutputFile("/Users/juju/outPipe.py")
exporter.write()

#for Id in objList.keySet():
#    obj = pipeline.getVtkObject(Id)
#    print obj.getId(), "--->", obj.getName(), obj.getType(), obj.getInputConnectivityList(), "---", obj.getOutputConnectivityList()


