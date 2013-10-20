/**
 * Copyright (c) 2013, ControlsFX
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *     * Neither the name of ControlsFX, any associated website, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL CONTROLSFX BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.controlsfx.samples.checked;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.samples.Utils;

import fxsampler.Sample;

public class HelloCheckComboBox extends Application implements Sample {
    
    @Override public String getSampleName() {
        return "CheckComboBox";
    }
    
    @Override public String getJavaDocURL() {
        return Utils.JAVADOC_BASE + "org/controlsfx/control/CheckComboBox.html";
    }
    
    @Override public boolean includeInSamples() {
        return true;
    }
    
    @Override public Node getPanel(Stage stage) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(30, 30, 0, 30));
        
        int row = 0;
        
        final ObservableList<String> strings = FXCollections.observableArrayList();
        for (int i = 0; i <= 100; i++) {
            strings.add("Item " + i);
        }

        // normal ComboBox
        grid.add(new Label("Normal ComboBox: "), 0, row);
        grid.add(new ComboBox<String>(strings), 1, row++);
        
        // CheckComboBox
        final CheckComboBox<String> cbcb = new CheckComboBox<String>(strings);
        cbcb.getCheckModel().getSelectedIndices().addListener(new ListChangeListener<Integer>() {
            @Override public void onChanged(ListChangeListener.Change<? extends Integer> c) {
                System.out.println(cbcb.getCheckModel().getSelectedIndices());
            }
        });
        cbcb.getCheckModel().getSelectedItems().addListener(new ListChangeListener<String>() {
            @Override public void onChanged(ListChangeListener.Change<? extends String> c) {
                System.out.println(cbcb.getCheckModel().getSelectedItems());
            }
        });
        grid.add(new Label("CheckComboBox: "), 0, row);
        grid.add(cbcb, 1, row++);
        
        return grid;
    }
    
    @Override public void start(Stage stage) throws Exception {
        stage.setTitle("CheckComboBox Demo");
        
        
        Scene scene = new Scene((Parent) getPanel(stage), 550, 550);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
