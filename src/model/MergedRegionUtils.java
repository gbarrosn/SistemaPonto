/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author gbarrosn
 */


public class MergedRegionUtils {

    public static List<MergedRegion> identifyMergedRegions(Sheet sheet) {
        List<MergedRegion> mergedRegions = new ArrayList<>();
        int numMergedRegions = sheet.getNumMergedRegions();

        for (int i = 0; i < numMergedRegions; i++) {
            org.apache.poi.ss.util.CellRangeAddress region = sheet.getMergedRegion(i);
            mergedRegions.add(new MergedRegion(region.getFirstRow(), region.getLastRow(), region.getFirstColumn(), region.getLastColumn()));
        }

        return mergedRegions;
    }

    public static boolean isMergedCell(Cell cell, List<MergedRegion> mergedRegions) {
        for (MergedRegion region : mergedRegions) {
            if (region.contains(cell.getRowIndex(), cell.getColumnIndex())) {
                return true;
            }
        }
        return false;
    }

    public static int getMergedRegionColumns(Cell cell, List<MergedRegion> mergedRegions) {
        for (MergedRegion region : mergedRegions) {
            if (region.contains(cell.getRowIndex(), cell.getColumnIndex())) {
                return region.getLastColumn() - region.getFirstColumn() + 1;
            }
        }
        return 1; // If the cell is not merged, it spans 1 column
    }

    public static int getMergedRegionRows(Cell cell, List<MergedRegion> mergedRegions) {
        for (MergedRegion region : mergedRegions) {
            if (region.contains(cell.getColumnIndex(), cell.getRowIndex())) {
                // Check if the current cell is in the first row of the merged region
                if (cell.getRowIndex() == region.getFirstRow()) {
                    return region.getLastRow() - region.getFirstRow() + 1;
                } else {
                    // If not the first row, the cell doesn't contribute to the rowspan
                    return 0;
                }
            }
        }
        return 1; // If the cell is not merged, it spans 1 row
    }
    

    public static class MergedRegion {
        private final int firstRow;
        private final int lastRow;
        private final int firstColumn;
        private final int lastColumn;

        public MergedRegion(int firstRow, int lastRow, int firstColumn, int lastColumn) {
            this.firstRow = firstRow;
            this.lastRow = lastRow;
            this.firstColumn = firstColumn;
            this.lastColumn = lastColumn;
        }

        public boolean contains(int rowIndex, int columnIndex) {
            return firstRow <= rowIndex && rowIndex <= lastRow && firstColumn <= columnIndex && columnIndex <= lastColumn;
        }

        public int getLastColumn() {
            return lastColumn;
        }

        public int getFirstColumn() {
            return firstColumn;
        }

        public int getLastRow() {
            return lastRow;
        }

        public int getFirstRow() {
            return firstRow;
        }
    }

    public static Cell getNextCell(Cell cell, List<MergedRegion> mergedRegions) {
        int nextColumn = cell.getColumnIndex() + getMergedRegionColumns(cell, mergedRegions);
        return cell.getRow().getCell(nextColumn);

    }


 }
