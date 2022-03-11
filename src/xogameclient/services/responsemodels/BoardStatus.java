/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.services.responsemodels;

/**
 *
 * @author amin
 */
public enum BoardStatus {
    NotDetermined,
    Row0,
    Row1,
    Row2,
    Col0,
    Col1,
    Col2,
    DiagonalTopLeftToBottomRight,
    DiagonalTopRightToBottomLeft,
    Tie
}
