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
	
	
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Button sendButton = new Button("Start!");
		final TextBox nameField = new TextBox();
		final Label errorLabel = new Label();
		final Button undoButton = new Button("undo");


		// We can add style names to widgets
		sendButton.addStyleName("sendButton");
		undoButton.setVisible(false);

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel.get("nameFieldContainer").add(nameField);
		RootPanel.get("sendButtonContainer").add(sendButton);
		RootPanel.get("errorLabelContainer").add(errorLabel);
		RootPanel.get("actionsContainer").add(undoButton);

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

				if (ghostId != null && dropId != null) {
					greetingService.getMovedTerm(ghostId, dropId, 
						new AsyncCallback<TermResponse>() {
						public void onFailure(Throwable caught) {
						}
	
						public void onSuccess(TermResponse term) {
							addToHistory(formulaHtml.getHTML());
							undoButton.setVisible(true);
							formulaHtml.setHTML(term.getHtml());
							errorLabel.setText(term.getText());
						}
					});
				} else if (ghostId == null) {
					Integer operatorId = getOperatorIdFromHtml(x.toString());
					greetingService.performOperation(operatorId, dropId,
						new AsyncCallback<TermResponse>() {
						public void onFailure(Throwable caught) {
						}

						public void onSuccess(TermResponse term) {
							addToHistory(formulaHtml.getHTML());
							undoButton.setVisible(true);
							formulaHtml.setHTML(term.getHtml());
							errorLabel.setText(term.getText());
						}
					});			
				}
			}
		}
		
		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			public void onClick(ClickEvent event) {
				sendFormulaToServer();
			}

			
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendFormulaToServer();
				}
			}

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
		
		// Create a handler for the sendButton and nameField
		class ActionHandler implements ClickHandler, KeyUpHandler {
			public void onClick(ClickEvent event) {
				undoStep();
			}

			private void undoStep() {
				greetingService.undoStep(
					new AsyncCallback<String>() {
						public void onFailure(Throwable caught) {
							// Show the RPC error message to the user
							formulaHtml.setText(caught.getMessage());
						}

						public void onSuccess(String term) {
							formulaHtml.setHTML(term);
							removeLastFromHistory(undoButton);
						}
					});
			}

			@Override
			public void onKeyUp(KeyUpEvent event) {				
			}
		}


		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
		sendButton.addClickHandler(handler);
		nameField.addKeyUpHandler(handler);
		
		ActionHandler actionHandler = new ActionHandler();
		undoButton.addClickHandler(actionHandler);
		
		FormulaHandler formulaHandler = new FormulaHandler();
		formulaHtml.addMouseUpHandler(formulaHandler);
	}
	
	private void addToHistory(String term) {
		final Element historyList = RootPanel.get("history").getElement();
		LIElement liElement = Document.get().createLIElement();
	    liElement.setInnerHTML(term);
		historyList.insertFirst(liElement);
	}
	
	private void removeLastFromHistory(Button undoButton) {
		final Element historyList = RootPanel.get("history").getElement();
		historyList.removeChild(historyList.getChild(0));
		System.out.println("child count: " + historyList.getChildCount());
		if (historyList.getChildCount() == 1) {
			undoButton.setVisible(false);
		}
	}
	
	private Integer getIdFromHtml(String html) {
		if (html.contains("data-id=\"")) {
			String afterId = html.split("data-id=\"")[1];
			String beforeQuote = afterId.split("\"")[0];
			return Integer.valueOf(beforeQuote);
		}
		return null;
	}
	
	private Integer getOperatorIdFromHtml(String html) {
		if (html.contains("data-operator-id=\"") && html.contains("active") && !html.contains("term")) {
			String afterId = html.split("data-operator-id=\"")[1];
			String beforeQuote = afterId.split("\"")[0];
			return Integer.valueOf(beforeQuote);
		}
		return null;
	}
}
