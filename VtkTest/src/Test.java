import vtk.vtkActor;
import vtk.vtkPolyDataMapper;
import vtk.vtkRenderWindow;
import vtk.vtkRenderWindowInteractor;
import vtk.vtkRenderer;
import vtk.vtkSphereSource;
import vtk.vtkSuperquadricSource;

public class Test {
	// Constructeur statique qui charge la bibliothèque VTK.
	// Les bibliothèques doivent être dans le path de votre système.
	static {
		System.loadLibrary("vtkCommonJava");
		System.loadLibrary("vtkFilteringJava");
		System.loadLibrary("vtkIOJava");
		System.loadLibrary("vtkImagingJava");
		System.loadLibrary("vtkGraphicsJava");
		System.loadLibrary("vtkRenderingJava");
		System.loadLibrary("vtkHybridJava");
	}

	public static void main(String[] args) {
		//test();
		exTorus();
	}

	public static void test() {
		// Créer une géométrie sphérique
		vtkSphereSource sphere = new vtkSphereSource();
		sphere.SetRadius(1.0);
		sphere.SetThetaResolution(18);
		sphere.SetPhiResolution(18);

		// Transforme la géométrie en primitives graphiques (OpenGL dans notre
		// cas)
		vtkPolyDataMapper map = new vtkPolyDataMapper();
		map.SetInput(sphere.GetOutput());

		// L'acteur représente l'entitée géométrique.
		// Il permet de définir sa position, son orientation, sa couleur, etc.
		vtkActor aSphere = new vtkActor();
		aSphere.SetMapper(map);
		aSphere.GetProperty().SetColor(0, 0, 1);

		// Nous créons un renderer qui va faire le rendu de notre entitée.
		vtkRenderer ren1 = new vtkRenderer();
		ren1.AddActor(aSphere);
		ren1.SetBackground(1, 1, 1);

		// Nous créons une fenêtre de rendu
		vtkRenderWindow renWin = new vtkRenderWindow();
		renWin.AddRenderer(ren1);
		renWin.SetSize(300, 300);

		// Nous créons un interactor qui permet de bouger la caméra.
		vtkRenderWindowInteractor iren = new vtkRenderWindowInteractor();
		iren.SetRenderWindow(renWin);

		// Nous lançons le rendu et l'interaction
		renWin.Render();
		iren.Start();
	}
	
	public static void exTorus() {
		// Créer une géométrie sphérique
		vtkSuperquadricSource torus = new vtkSuperquadricSource();
		torus.SetCenter(0,0,0);
		torus.SetPhiResolution(64);
		torus.SetThetaResolution(64);
		torus.SetToroidal(1);

		// Transforme la géométrie en primitives graphiques (OpenGL dans notre
		// cas)
		vtkPolyDataMapper map = new vtkPolyDataMapper();
		map.SetInput(torus.GetOutput());

		// L'acteur représente l'entitée géométrique.
		// Il permet de définir sa position, son orientation, sa couleur, etc.
		vtkActor aTorus = new vtkActor();
		aTorus.SetMapper(map);
		aTorus.GetProperty().SetColor(0, 0, 1);

		// Nous créons un renderer qui va faire le rendu de notre entitée.
		vtkRenderer ren1 = new vtkRenderer();
		ren1.AddActor(aTorus);
		ren1.SetBackground(1, 1, 1);

		// Nous créons une fenêtre de rendu
		vtkRenderWindow renWin = new vtkRenderWindow();
		renWin.AddRenderer(ren1);
		renWin.SetSize(300, 300);

		// Nous créons un interactor qui permet de bouger la caméra.
		vtkRenderWindowInteractor iren = new vtkRenderWindowInteractor();
		iren.SetRenderWindow(renWin);

		// Nous lançons le rendu et l'interaction
		renWin.Render();
		iren.Start();
	}
}
