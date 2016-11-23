
print "Start cameraDolly script demo"

print "importing modules..."
import cameraDolly
from cameraDolly import *
import math

print "DONE"

print "Initialisation track..."

initialPos = cassandra.getRendererPanel().GetRenderer().GetActiveCamera().GetPosition()
initialRadius = math.sqrt(initialPos[0]*initialPos[0] + initialPos[1]*initialPos[1] + initialPos[2]*initialPos[2])
scaling = 1.1

print initialPos[0], initialPos[1], initialPos[2]
print initialRadius

dolly = CameraDolly(cassandra.getRendererPanel())

#dolly.addLine( initialPos[0], initialPos[1], initialPos[2], -initialPos[0]*scaling, -initialPos[1]*scaling, -initialPos[2]*scaling, 30)
#dolly.addLine( -initialPos[0]*scaling, -initialPos[1]*scaling, -initialPos[2]*scaling, initialRadius, 0, 0, 30)
dolly.addLine( initialRadius, 0, 0, initialRadius, initialRadius, initialRadius, 50)
dolly.addLine(initialRadius, initialRadius, initialRadius, 0, 0, initialRadius, 50)
dolly.addLine(0, 0, initialRadius, 0, initialRadius, 0, 50)

print "DONE"

print "Action..."
dolly.moveCamera(0.1, 1)
print "End"

