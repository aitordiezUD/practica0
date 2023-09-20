package clases;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class VentanaGrandSlam extends JFrame {
	
		static TablaDatos tablaResultados;
		static TablaDatos tablaTenistas;
		static TablaDatos tablaTorneos;
		ArrayList<DatoTabular> resultados;
		ArrayList<DatoTabular> tenistas;
		
		
	public VentanaGrandSlam(ArrayList<DatoTabular> resultadosIn, ArrayList<DatoTabular> tenistasIn,ArrayList<DatoTabular> torneos) {
		this.setSize(700,700);
		this.setTitle("Grand Slams");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JTabbedPane paneles = new JTabbedPane();
		
		resultados = new ArrayList<DatoTabular>(resultadosIn);
		tenistas = new ArrayList<DatoTabular>(tenistasIn);
		
		tablaResultados =  new TablaDatos(resultados);
		tablaTenistas =  new TablaDatos(tenistas);
		tablaTorneos =  new TablaDatos(torneos);
		
		tablaResultados.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	System.out.println("Error");
                int filaSeleccionada = tablaResultados.getSelectedRow();
                int columnaSeleccionada = tablaResultados.getSelectedColumn();
                ArrayList<Tenista> listaAux = new ArrayList<Tenista>();
                for (DatoTabular d : tenistas) {
                	listaAux.add((Tenista) d);
                }
                Collections.sort(listaAux);
                DefaultTableModel modelo = (DefaultTableModel) tablaResultados.getModel();
                int ranking = listaAux.indexOf(HistoriaGrandSlams.tenistasMapaNombre.get(modelo.getValueAt(filaSeleccionada, columnaSeleccionada))) +1;
                if (columnaSeleccionada == 2 | columnaSeleccionada == 4) {
                	String mensaje = modelo.getValueAt(filaSeleccionada, columnaSeleccionada)+ "\n" + "Cabeza de serie en este torneo: " + 
                    modelo.getValueAt(filaSeleccionada, columnaSeleccionada+1) + "\nActaualmente es nº " + ranking + " en el ranking de Grand Slams \nHabiendo ganado "
                    + HistoriaGrandSlams.tenistasMapaNombre.get(modelo.getValueAt(filaSeleccionada, columnaSeleccionada)).getNumVictorias();
                    JOptionPane.showMessageDialog(null, mensaje, "Mensaje", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });
		
		JScrollPane scrollPaneTablaResultados = new JScrollPane(tablaResultados);
		JScrollPane scrollPaneTablaTenistas = new JScrollPane(tablaTenistas);
		JScrollPane scrollPaneTablaTorneos = new JScrollPane(tablaTorneos);
		
		JPanel panelResultados = new JPanel();
		panelResultados.setLayout(new BorderLayout());


		JPanel panelTablaRes = new JPanel();
		panelTablaRes.setLayout(new BorderLayout()); 
		panelTablaRes.add(scrollPaneTablaResultados, BorderLayout.CENTER);
		panelResultados.add(panelTablaRes, BorderLayout.CENTER);
		
		JPanel pnlBotonesResul = new JPanel(); 
		pnlBotonesResul.setPreferredSize(new Dimension(pnlBotonesResul.getPreferredSize().width, (int) (this.getHeight() * 0.1)));
		pnlBotonesResul.setLayout(new GridLayout(2,1));
		
		JPanel pnlBotonesResulSup = new JPanel();
		pnlBotonesResulSup.setLayout(new FlowLayout());
		JButton botonGuardar = new JButton("Guardar");
		botonGuardar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				HistoriaGrandSlams.guardarFichero();
			}
			
		});
		JButton botonAnadir = new JButton("Añadir");
		botonAnadir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				anadirResultado();
			}});
		pnlBotonesResulSup.add(botonAnadir);
		pnlBotonesResulSup.add(botonGuardar);
		
		JPanel pnlBotonesResulInf = new JPanel();
		JButton botonEvolucionRes = new JButton("Evolución ranking");
		botonEvolucionRes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				crearEvo();
			}
		});
		pnlBotonesResulInf.add(botonEvolucionRes);
		pnlBotonesResul.add(pnlBotonesResulSup);
		pnlBotonesResul.add(pnlBotonesResulInf);
		panelResultados.add(pnlBotonesResul, BorderLayout.SOUTH);
		
		JPanel panelTenistas = new JPanel();
		panelTenistas.setLayout(new BorderLayout());
		JPanel panelTablaTen = new JPanel();
		panelTablaTen.setLayout(new BorderLayout());
		panelTablaTen.add(scrollPaneTablaTenistas, BorderLayout.CENTER);
		panelTenistas.add(panelTablaTen, BorderLayout.CENTER);
		JPanel panelBotonTen = new JPanel();
		panelBotonTen.setPreferredSize(new Dimension(panelBotonTen.getPreferredSize().width, (int) (this.getHeight() * 0.1)));
		JButton botonEvoTen = new JButton("Evolución ranking"); 
		botonEvoTen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				crearEvo();
			}
			
		});;
		panelBotonTen.add(botonEvoTen);
		panelTenistas.add(panelBotonTen, BorderLayout.SOUTH);
		
		JPanel panelTorneos = new JPanel();
		panelTorneos.setLayout(new BorderLayout());
		JPanel panelTablaTor = new JPanel();
		panelTablaTor.setLayout(new BorderLayout());
		panelTablaTor.add(scrollPaneTablaTorneos, BorderLayout.CENTER);
		panelTorneos.add(panelTablaTor, BorderLayout.CENTER);
		JPanel panelBotonTor = new JPanel();
		panelBotonTor.setPreferredSize(new Dimension(panelBotonTor.getPreferredSize().width, (int) (this.getHeight() * 0.1)));
		JButton botonEvoTor = new JButton("Evolución ranking"); 
		botonEvoTor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				crearEvo();
			}
			
		});;
		panelBotonTor.add(botonEvoTor);
		panelTorneos.add(panelBotonTor, BorderLayout.SOUTH);
		
		paneles.add("Resultados",panelResultados);
        paneles.add("Tenistas",panelTenistas);
		paneles.add("Torneos",panelTorneos);
		
		add(paneles);
		
		paneles.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				  if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_N) {
					  anadirResultado();
				  }
				  if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_S) {
					  HistoriaGrandSlams.guardarFichero();
				  }
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		this.setVisible(true);
	}
	
	
	
	
	
	
	public void crearEvo() {
		int anyoInicial = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el año inicial de la evolución"));
		int anyoFinal = Integer.parseInt(JOptionPane.showInputDialog("Introduzca el año final de la evolución"));
		ArrayList<Resultado> listaAux = new ArrayList<Resultado>();
		for (DatoTabular dat: resultados) {
			listaAux.add((Resultado) dat);
		}
		Collections.sort(listaAux);
		GraficoEvo grafico = new GraficoEvo(tenistas, resultados, anyoInicial, anyoFinal);
		VentanaGraficoBarras vgb = new VentanaGraficoBarras(grafico);
	};
	
	public void anadirResultado() {
		int anyo = Integer.parseInt(JOptionPane.showInputDialog("Año del torneo"));
		String[] opciones = {"Australian Open", "Roland Garros", "Wimbledon", "US Open"};
        
        int selTorneo = JOptionPane.showOptionDialog(null, "Selecciona una opción", "Menú de opciones",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);
		Torneo t = HistoriaGrandSlams.torneosMapaCodigo.get(selTorneo + 1);
		String nGanador = JOptionPane.showInputDialog("Nombre del ganador:");
		String nacionalidadGanador = JOptionPane.showInputDialog("Nacionalidad del ganador:");
		int rGanador = Integer.parseInt(JOptionPane.showInputDialog("Ranking del ganador:"));
		Tenista ganador = new Tenista(null,null,0);
		if (HistoriaGrandSlams.tenistasMapaNombre.get(nGanador) == null) {
			ganador = new Tenista(nGanador, nacionalidadGanador, 1);
			HistoriaGrandSlams.tenistas.add(ganador);
			HistoriaGrandSlams.tenistasMapaNombre.put(nGanador, ganador);
			HistoriaGrandSlams.mapaVictoriasTenistas.put(ganador, 1);
		}else {
			ganador = HistoriaGrandSlams.tenistasMapaNombre.get(nGanador);
			ganador.setNumVictorias(ganador.getNumVictorias()+1);
			HistoriaGrandSlams.mapaVictoriasTenistas.replace(ganador, HistoriaGrandSlams.mapaVictoriasTenistas.get(ganador)+1);
		}
		
		
		String nFinalista = JOptionPane.showInputDialog("Nombre del finalista:");
		String nacionalidadFinalista = JOptionPane.showInputDialog("Nacionalidad del finalista:");
		int rFinalista = Integer.parseInt(JOptionPane.showInputDialog("Ranking del finalista:"));
		Tenista finalista = new Tenista(null,null,0);
		if (HistoriaGrandSlams.tenistasMapaNombre.get(nFinalista) == null) {
			finalista = new Tenista(nFinalista, nacionalidadFinalista, 0);
			HistoriaGrandSlams.tenistas.add(finalista);
			HistoriaGrandSlams.tenistasMapaNombre.put(nFinalista, finalista);
			HistoriaGrandSlams.mapaVictoriasTenistas.put(finalista, 0);
		}
		String resultado = JOptionPane.showInputDialog("Resultado:");
		
		Resultado r = new Resultado(anyo, t, ganador, rGanador, finalista, rFinalista, resultado);
		HistoriaGrandSlams.resultados.add(r);
		DefaultTableModel modeloTablaResultados = (DefaultTableModel) tablaResultados.getModel();
		ArrayList<String> datosFilaRes = new ArrayList<>();
		datosFilaRes.add(Integer.toString(anyo));
		datosFilaRes.add(t.getNombre());
		datosFilaRes.add(nGanador);
		datosFilaRes.add(Integer.toString(rGanador));
		datosFilaRes.add(nFinalista);
		datosFilaRes.add(Integer.toString(rFinalista));
		datosFilaRes.add(resultado);
		Object[] rowRes = datosFilaRes.toArray();
		modeloTablaResultados.addRow(rowRes);
		modeloTablaResultados.fireTableDataChanged();
		tablaResultados.repaint();
		
		DefaultTableModel modeloTablaTenistas = (DefaultTableModel) tablaTenistas.getModel();
		ArrayList<String> datosFilaTenGan = new ArrayList<>();
		datosFilaTenGan.add(ganador.getNombre());
		datosFilaTenGan.add(ganador.getNacionalidad());
		datosFilaTenGan.add(Integer.toString(ganador.getNumVictorias()));
		Object[] rowGan = datosFilaTenGan.toArray();
		modeloTablaTenistas.addRow(rowGan);
		
		ArrayList<String> datosFilaTenFin = new ArrayList<>();
		datosFilaTenFin.add(finalista.getNombre());
		datosFilaTenFin.add(finalista.getNacionalidad());
		datosFilaTenFin.add(Integer.toString(finalista.getNumVictorias()));
		Object[] rowFin = datosFilaTenFin.toArray();
		modeloTablaTenistas.addRow(rowFin);
		
		modeloTablaTenistas.fireTableDataChanged();
		tablaTenistas.ordenarPorVictorias();
		
	};
	
}
