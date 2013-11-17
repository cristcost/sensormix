package com.google.developers.gdgfirenze.admin.client.cell;

import com.google.developers.gdgfirenze.model.AbstractSample;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class SampleCell extends AbstractCell<AbstractSample> {

  @Override
  public void render(Context context, AbstractSample sample, SafeHtmlBuilder sb) {
    // Value can be null, so do a null check..
    if (sample == null) {
      return;
    }

    sb.appendHtmlConstant("<table><tr><td>");
    sb.appendEscaped(sample.getTime().toString());
    sb.appendHtmlConstant("</td><td>");
    sb.appendEscaped(sample.getType());
    sb.appendHtmlConstant("</td></td></table>");
  }

}
