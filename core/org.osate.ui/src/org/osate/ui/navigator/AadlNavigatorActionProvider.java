package org.osate.ui.navigator;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.xtext.ui.editor.IURIEditorOpener;
import org.osate.aadl2.modelsupport.EObjectURIWrapper;
import org.osate.xtext.aadl2.ui.internal.Aadl2Activator;
import org.osate.xtext.aadl2.ui.resource.ContributedAadlStorage;

import com.google.inject.Injector;

public class AadlNavigatorActionProvider extends CommonActionProvider {
	private final IURIEditorOpener editorOpener;
	private final Action openFileAction;

	public AadlNavigatorActionProvider() {
		Injector injector = Aadl2Activator.getInstance().getInjector(Aadl2Activator.ORG_OSATE_XTEXT_AADL2_AADL2);
		editorOpener = injector.getInstance(IURIEditorOpener.class);
		openFileAction = new Action() {
			{
				setText("&Open");
				setToolTipText("Open in an AADL editor");
			}

			@Override
			public void run() {
				Object[] selectedElements = getActionSite().getStructuredViewer().getStructuredSelection().toArray();
				for (Object selected : selectedElements) {
					if (selected instanceof ContributedAadlStorage) {
						editorOpener.open(((ContributedAadlStorage) selected).getUri(), true);
					} else if (selected instanceof EObjectURIWrapper) {
						editorOpener.open(((EObjectURIWrapper) selected).getUri(), true);
					}
				}
			}
		};
	}

	@Override
	public void fillContextMenu(final IMenuManager aMenu) {
		if (getContext().getSelection().isEmpty()) {
			return;
		}

		if (openFileAction.isEnabled()) {
			aMenu.insertAfter(ICommonMenuConstants.GROUP_OPEN, openFileAction);
		}
	}

	@Override
	public void fillActionBars(IActionBars actionBars) {
		super.fillActionBars(actionBars);
		actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openFileAction);
	}
}