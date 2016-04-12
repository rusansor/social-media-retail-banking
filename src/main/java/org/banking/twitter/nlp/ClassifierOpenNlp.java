package org.banking.twitter.nlp;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class ClassifierOpenNlp {

	private static String toto = "@user1 Buenas tardes Emilio. Te hemos respondido por DM. Un saludo";

	private static String calico = "¿Quieres saber cómo rellenar un cheque correctamente? Sigue estos pequeños pasos https://t.co/nhLuRdKrwj";
	
	private static String calico2 = "@user2 Te hemos contestado con detalle por DM también Luis. Esperamos que te sirva de aclaración/resolución. Un saludo. Gracias";

	public void train() {
		DoccatModel model = null;
		InputStream dataIn;
		try {
			dataIn = new FileInputStream("src/main/resources/twitter.train");
			OutputStream dataOut = new FileOutputStream("src/main/resources/twitter.model");
			ObjectStream<String> lineStream;
			lineStream = new PlainTextByLineStream(dataIn, "UTF-8");
			ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);
			model = DocumentCategorizerME.train("es", sampleStream);
			OutputStream modelOut = null;
			modelOut = new BufferedOutputStream(dataOut);
			model.serialize(modelOut);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void classify(String inputText) {
		InputStream modelIn;
		try {
			modelIn = new FileInputStream(new File("src/main/resources/twitter.model"));
			DoccatModel model = new DoccatModel(modelIn);
			DocumentCategorizerME categorizer = new DocumentCategorizerME(model);

			double[] outcomes = categorizer.categorize(inputText);
			for (int i = 0; i < categorizer.getNumberOfCategories(); i++) {
				String category = categorizer.getCategory(i);
				System.out.println(category + " - " + outcomes[i]);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ClassifierOpenNlp classifierOpenNlp = new ClassifierOpenNlp();
		classifierOpenNlp.train();
		
		classifierOpenNlp.classify(toto);
		classifierOpenNlp.classify(calico);
		classifierOpenNlp.classify(calico2);
	}
}



