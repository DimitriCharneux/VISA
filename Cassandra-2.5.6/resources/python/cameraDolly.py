
import time

class CameraDolly:

    def __init__(self, cassandraRendererPanel):
        self.renderePanel = cassandraRendererPanel
        self.renderePanel.GetRenderer().SetLightFollowCamera(1)
        self.camera = self.renderePanel.GetRenderer().GetActiveCamera()

        self.renderePanel.GetRenderer().GetActiveCamera().SetClippingRange(0.1, 1000)

        self.initTrajectoryAndOrientation()

    def initTrajectoryAndOrientation(self): 
        self.traj = []
    
    def addLine(self, xIn, yIn, zIn, xOut, yOut, zOut, nbSteps):    
        for pos in xrange(nbSteps):
            x = (xOut - xIn)/nbSteps*pos + xIn;
            y = (yOut - yIn)/nbSteps*pos + yIn;
            z = (zOut - zIn)/nbSteps*pos + zIn;
            self.traj.append([x, y, z]);
        
    def moveCamera(self, period, lockOrientationOnCenter):
        for pos in self.traj:
            self.camera.SetPosition(pos)
            #if (lockOrientationOnCenter == 1):
            #    self.camera.SetOrientation(0.0, 0.0, 0.0)
            #elif ( len(orientationList) > 0):
            #    self.camera.SetOrientation( orientationList[traj.index(pos)] )
            self.renderePanel.UpdateLight()
            self.renderePanel.validateViewAndGo()
            time.sleep(period)
