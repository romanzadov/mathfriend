package com.mathfriend.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.EventTarget;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.mathfriend.shared.FieldVerifier;


public class MathFriend implements EntryPoint {
	
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Start!");
		final TextBox nameField = new TextBox();
		final Label errorLabel = new Label();


		// We can add style names to widgets
		sendButton.addStyleName("sendButton");

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);

		// Focus the cursor on the name field when the app loads
		nameField.setFocus(true);
		nameField.selectAll();
		
		final HTML formulaHtml = new HTML();
		RootPanel.get("formulaContainer").add(formulaHtml);
			
		class FormulaHandler implements MouseUpHandler {
			@Override
			public void onMouseUp(MouseUpEvent event) {
				EventTarget x = event.getNativeEvent().getEventTarget();
				Integer dropId = getIdFromHtml(x.toString());
				
				String html =  RootPanel.get("ghostContainer").getElement().getInnerHTML();
				Integer ghostId = getIdFromHtml(html);
				
				greetingService.getMovedTerm(ghostId, dropId, 
					new AsyncCallback<String>() {
					public void onFailure(Throwable caught) {
					}

					public void onSuccess(String term) {
						addToHistory(formulaHtml.getHTML());
						formulaHtml.setHTML(term.toString());
					}
				});
			}
		}
		
		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendFormulaToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendFormulaToServer();
				}
			}

			/**
			 * Send the name from the nameField to the server and wait for a response.
			 */
			private void sendFormulaToServer() {
				// First, we validate the input.
				errorLabel.setText("");
				String formula = nameField.getText();
				if (!FieldVerifier.isValidFormula(formula)) {
					errorLabel.setText("Invalid character(s)");
					return;
				}

				// Then, we send the input to the server.
				greetingService.getFirstCompoundTermHtml(formula,
						new AsyncCallback<String>() {
							public void onFailure(Throwable caught) {
								// Show the RPC error message to the user
								formulaHtml.setText(caught.getMessage());
							}

							public void onSuccess(String term) {
								formulaHtml.setHTML(term);
								nameField.setVisible(false);
								sendButton.setVisible(false);
							}
						});
			}
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
		
		FormulaHandler formulaHandler = new FormulaHandler();
		formulaHtml.addMouseUpHandler(formulaHandler);
	}
	
	private void addToHistory(String term) {
		final Element historyList = RootPanel.get("history").getElement();
		LIElement liElement = Document.get().createLIElement();
	    liElement.setInnerHTML(term);
		historyList.insertFirst(liElement);
	}
	
	private Integer getIdFromHtml(String html) {
		if (html.contains("data-id=\"")) {
			String afterId = html.split("data-id=\"")[1];
			String beforeQuote = afterId.split("\"")[0];
			return Integer.valueOf(beforeQuote);
		}
		return null;
	}
}
