/**
 *Clase <code>ControladorApp</code>.
 *Clase que funciona como controlador para la vista en XML.
 *Esta clase únicamente contiene escuchas para los botones y controla su comportamiento.
 *@author <a href="mailto:pablo.t645@hotmail.com">Pablo G.</a>
 *@version 1.0
 *Copyright 2016 Pablo G.
 */
package EClosure;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeTableColumn;
import javafx.collections.ObservableList;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.*;
import java.util.HashSet;
import javafx.scene.text.Text;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.util.Callback;
import javafx.scene.control.ListCell;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDFont;

public class ControladorApp{

	AFNe automata;

	public ControladorApp(){
		this.automata = new AFNe();
	}

	@SuppressWarnings("unchecked") @FXML private void initialize() {
		actualizaListasDesp();
		actualizaDatosTabla();
	}

	@FXML private TreeTableView<Estado> auTT;

	@SuppressWarnings("unchecked") private void actualizaDatosTabla(){
		auTT.getColumns().clear();

		TreeTableColumn<Estado, String> estados = new TreeTableColumn<>();
    	estados.setText("Q");
    	estados.setEditable(false);
    	estados.setSortable(true);
    	estados.setResizable(true);
		estados.setCellValueFactory(e -> {
			return new ReadOnlyStringWrapper(e.getValue().getValue().getNombre());
		});

		TreeTableColumn<Estado, String> transS = new TreeTableColumn<>();
    	transS.setText("Σ");
    	transS.setEditable(false);
    	transS.setSortable(true);
    	transS.setResizable(true);
		for (String s:automata.getSimbolos()) {
			if (!s.equals(AFNe.EPSILON)) {	
				TreeTableColumn<Estado, String> transSim = new TreeTableColumn<>();
		    	transSim.setText(s);
		    	transSim.setEditable(false);
		    	transSim.setSortable(true);
		    	transSim.setResizable(true);
				transSim.setCellValueFactory(e -> {
					String ests = "{";
					for (Transicion t:e.getValue().getValue().getTransiciones()) {
						if (s.equals(t.getSimbolo())) {
							for (Estado te:t.getEstados()) {
								ests = ests + te.getNombre();
								ests = ests + ",";
							}
						}
					}
					if (ests.length()!=1) {
						ests = ests.substring(0,ests.length()-1) + "}";
					}else{
						ests = ests + "}";
					}
					return new ReadOnlyStringWrapper(ests);
				});			
				transS.getColumns().add(transSim);
			}
		}

		TreeTableColumn<Estado, String> transE = new TreeTableColumn<>();
    	transE.setText("ε");
    	transE.setEditable(false);
    	transE.setSortable(true);
    	transE.setResizable(true);
		transE.setCellValueFactory(e -> {
			String ests = "{";
			for (Transicion t:e.getValue().getValue().getTransiciones()) {
				if (AFNe.EPSILON.equals(t.getSimbolo())) {
					for (Estado te:t.getEstados()) {
						ests = ests + te.getNombre();
						ests = ests + ",";
					}
				}
			}
			if (ests.length()!=1) {
				ests = ests.substring(0,ests.length()-1) + "}";
			}else{
				ests = ests + "}";
			}
			return new ReadOnlyStringWrapper(ests);
		});

		TreeTableColumn<Estado, String> fin = new TreeTableColumn<>();
    	fin.setText("F");
    	fin.setEditable(false);
    	fin.setSortable(true);
    	fin.setResizable(true);
		fin.setCellValueFactory(e -> {
			if (e.getValue().getValue().geteFinal()) {
				return new ReadOnlyStringWrapper("1");
			}else{
				return new ReadOnlyStringWrapper("0");
			}
		});		

		auTT.getColumns().add(estados);
		auTT.getColumns().add(transS);
		auTT.getColumns().add(transE);
		auTT.getColumns().add(fin);

		TreeItem<Estado> root = new TreeItem<>(automata.getInicial());
		for (Estado e:automata.getEstados()) {
			root.getChildren().add(new TreeItem<>(e));
		}
		auTT.setRoot(root);
		auTT.setShowRoot(false);
	}

	@FXML private ChoiceBox<Estado> qModiCB;
	@FXML private ChoiceBox<Estado> qElimCB;
	@FXML private ChoiceBox<Estado> dAgreECB;
	@FXML private ChoiceBox<String> dAgreSCB;
	@FXML private ChoiceBox<Estado> dModiECB;
	@FXML private ChoiceBox<String> dModiSCB;
	
	@SuppressWarnings("unchecked") private void actualizaListasDesp(){
		qModiCB.getItems().clear();
		for (Estado e:automata.getEstados()) {
			qModiCB.getItems().add(e);
		}

		qElimCB.getItems().clear();
		for (Estado e:automata.getEstados()) {
			if (!e.equals(automata.getInicial())) {
				qElimCB.getItems().add(e);
			}
		}

		dAgreECB.getItems().clear();
		for (Estado e:automata.getEstados()) {
			dAgreECB.getItems().add(e);
		}

		dAgreSCB.getItems().clear();
		for (String s:automata.getSimbolos()) {
			dAgreSCB.getItems().add(s);
		}

		dModiECB.getItems().clear();
		for (Estado e:automata.getEstados()) {
			dModiECB.getItems().add(e);
		}

		dModiSCB.getItems().clear();
		for (String s:automata.getSimbolos()) {
			dModiSCB.getItems().add(s);
		}
	}

	@FXML private TextField sAgreT;
	@FXML private Text sAgreErr;

	@FXML protected void sAgreB(ActionEvent event){
		if (!sAgreT.getText().equals("")) {
			String errores = "";
			String[] simbolos = sAgreT.getText().split(";");
			String[] simbolosV = new String[simbolos.length];
			int i = 0;
			for (String s:simbolos) {
				if (s.length()!=1) {
					simbolosV[i] = "[" + s + "]";
				}else{
					simbolosV[i] = s;
				}
				i++;
			}
			simbolos = simbolosV;
			for (String s:simbolos) {
				if (!s.equals("")) {
					try{
						automata.agregaSimbolo(s);
					}catch(SymbolAlreadyExistsException e){
						errores = errores + e + "\n";
					}
				}
			}
			sAgreErr.setText(errores);

			actualizaListasDesp();
			actualizaDatosTabla();
		}
	}


	@FXML private TextField qAgreT;
	@FXML private CheckBox qAgreB;
	@FXML private Text qAgreErr;

	@FXML protected void qAgreB(ActionEvent event){
		if (!qAgreT.getText().equals("")) {
			String errores = "";
			try{
				automata.agregaEstado(new Estado(qAgreT.getText(),qAgreB.isSelected()));
			}catch(Exception e){
				errores = "" + e;
			}
			
			qAgreErr.setText(errores);
			
			actualizaListasDesp();
			actualizaDatosTabla();
		}
	}

	@FXML private TextField qModiT;
	@FXML private CheckBox qModiB;
	@FXML private Text qModiErr;

	@FXML protected void qModiB(ActionEvent event){
		if (!qModiT.getText().equals("")&&qModiCB.getValue()!=null) {
			String errores = "";
			try{
				automata.modificaEstado(qModiCB.getValue(),qModiT.getText(),qModiB.isSelected());
			}catch(Exception e){
				errores = "" + e;
			}
			
			qModiErr.setText(errores);
			
			actualizaListasDesp();
			actualizaDatosTabla();
		}
	}

	@FXML protected void qElimB(ActionEvent event){
		if (qElimCB.getValue()!=null) {
			try{
				automata.eliminaEstado(qElimCB.getValue());
			}catch(Exception e){
			}
			
			actualizaListasDesp();
			actualizaDatosTabla();
		}
	}

	@FXML private TextField dAgreET;
	@FXML private Text dAgreErr;
	@FXML protected void dAgreB(ActionEvent event){
		if (dAgreECB.getValue()!=null&&dAgreSCB.getValue()!=null&&!dAgreET.getText().equals("")) {
			String errores = "";
			String[] estadost = dAgreET.getText().split(";");
			for (String e:estadost) {
				if (!e.equals("")) {
					try{
						automata.getEstado(e);
					}catch(Exception ex){
						errores = errores + ex + "\n";
					}
				}
			}
			if (errores.equals("")) {
				try{
					Transicion t = null;
					for (Transicion trans:dAgreECB.getValue().getTransiciones()) {
						if (trans.getSimbolo().equals(dAgreSCB.getValue())) {
							t = trans;
						}
					}
					HashSet<Estado> estadostrans = new HashSet<Estado>();
					for (String e:estadost) {
						estadostrans.add(automata.getEstado(e));
					}
					if (t != null) {
						errores = errores + "Modifica.\n";
						automata.eliminaTrans(dAgreECB.getValue(),dAgreSCB.getValue());
					}else{
						errores = errores + "Agrega.\n";
					}
					automata.agregaTrans(dAgreECB.getValue(),dAgreSCB.getValue(),estadostrans);
				}catch(Exception ex){
					errores = errores + ex + "\n";
				}			
			}
			
			dAgreErr.setText(errores);

			actualizaListasDesp();
			actualizaDatosTabla();
		}
	}

	@FXML private Text dModiErr;
	@FXML protected void dModiB(ActionEvent event){
		if (dModiECB.getValue()!=null&&dModiSCB.getValue()!=null) {
			String errores = "";
			try{
				automata.eliminaTrans(dModiECB.getValue(),dModiSCB.getValue());
			}catch(Exception e){
				errores = errores + e + "\n";
			}
			
			dModiErr.setText(errores);

			actualizaListasDesp();
			actualizaDatosTabla();
		}
	}

	@FXML protected void calculaEC(ActionEvent event){
		HashSet<Estado>[][] info = automata.eCerradura();
		try{
			PDDocument doc = new PDDocument();
        	PDPage page = new PDPage();

            doc.addPage(page);
	        PDFont font = PDType1Font.HELVETICA_BOLD;

	        PDPageContentStream content = new PDPageContentStream(doc, page);
	        for (int i = 0; i < info.length; i++) {
	        	content.beginText();
	        	content.setFont( font, 10 );
	       		content.moveTextPositionByAmount( 100, 700 + (i*15) );
	        	String ds = AFNe.EPSILON + "-cerradura(";
	        	for (Estado e:info[i][0]) {
	        		ds = ds + e.getNombre();
	        	}
	        	ds = ds + ") = {";
				for (Estado e:info[i][1]) {
					ds = ds + e.getNombre() + ",";
				}
				ds = ds.substring(0,ds.length()-1) + "}\n";
				System.out.println(ds);
	        	content.drawString(ds);
	        	content.endText();
	        }
	        content.close();
	        doc.save("[EClosure]ECerraduraAFNe.pdf");
	        doc.close();
	    } catch (Exception e){
	        System.out.println(e);
	    }
	}
}