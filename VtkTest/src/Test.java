import vtk.vtkActor;
import vtk.vtkCleanPolyData;
import vtk.vtkCurvatures;
import vtk.vtkLookupTable;
import vtk.vtkOBJReader;
import vtk.vtkPolyDataMapper;
import vtk.vtkRenderWindow;
import vtk.vtkRenderWindowInteractor;
import vtk.vtkRenderer;
import vtk.vtkSphereSource;
import vtk.vtkSuperquadricSource;
import vtk.vtkTriangleFilter;

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
		// test();
		// exTorus();
		exVache();
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
		torus.SetCenter(0, 0, 0);
		torus.SetPhiResolution(64);
		torus.SetThetaResolution(64);
		torus.SetToroidal(1);

		// vtkfilter
		vtkTriangleFilter triangleFilter = new vtkTriangleFilter();
		triangleFilter.AddInput(torus.GetOutput());

		vtkCleanPolyData clean = new vtkCleanPolyData();
		clean.SetTolerance(0.005);
		clean.AddInput(triangleFilter.GetOutput());

		// courbe gausienne
		vtkCurvatures curve = new vtkCurvatures();
		curve.SetCurvatureTypeToGaussian();
		curve.AddInput(clean.GetOutput());

		vtkCurvatures curve2 = new vtkCurvatures();
		curve2.SetCurvatureTypeToMean();
		curve2.AddInput(clean.GetOutput());

		// coloration
		vtkLookupTable lut1 = new vtkLookupTable();
		lut1.SetNumberOfColors(256);
		lut1.SetHueRange(0.15, 1.0);
		lut1.SetSaturationRange(1.0, 1.0);
		lut1.SetValueRange(1.0, 1.0);
		lut1.SetAlphaRange(1.0, 1.0);
		lut1.SetRange(-20, 20);

		vtkLookupTable lut2 = new vtkLookupTable();
		lut2.SetNumberOfColors(256);
		lut2.SetHueRange(0.15, 1.0);
		lut2.SetSaturationRange(1.0, 1.0);
		lut2.SetValueRange(1.0, 1.0);
		lut2.SetAlphaRange(1.0, 1.0);
		lut2.SetRange(0, 4);

		// Transforme la géométrie en primitives graphiques (OpenGL dans notre
		// cas)
		vtkPolyDataMapper map = new vtkPolyDataMapper();
		map.SetInput(curve.GetOutput());
		map.SetLookupTable(lut1);
		map.SetUseLookupTableScalarRange(1);

		vtkPolyDataMapper map2 = new vtkPolyDataMapper();
		map2.SetInput(curve2.GetOutput());
		map2.SetLookupTable(lut2);
		map2.SetUseLookupTableScalarRange(1);

		// L'acteur représente l'entitée géométrique.
		// Il permet de définir sa position, son orientation, sa couleur, etc.
		vtkActor aTorus = new vtkActor();
		aTorus.SetMapper(map);
		aTorus.SetPosition(0, 0, 0);

		vtkActor aTorus2 = new vtkActor();
		aTorus2.SetMapper(map2);
		aTorus2.SetPosition(1, 0, 0);

		// aTorus.GetProperty().SetColor(0, 0, 1);

		// Nous créons un renderer qui va faire le rendu de notre entitée.
		vtkRenderer ren1 = new vtkRenderer();
		ren1.AddActor(aTorus);
		ren1.AddActor(aTorus2);
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

	public static void exVache() {
		// Créer une géométrie sphérique
		vtkOBJReader vache = new vtkOBJReader();
		vache.SetFileName("./3Dmodels/cow.obj");

		// Transforme la géométrie en primitives graphiques (OpenGL dans notre
		// cas)
		vtkPolyDataMapper map = new vtkPolyDataMapper();
		map.SetInput(vache.GetOutput());

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
}
