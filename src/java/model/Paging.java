/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Paging {

    public Paging() {
    }
    private int nrpp; // so luong ban ghi hien thi tren moi trang
    private int index; // chi so cua trang hien tai
    private int size; // tong so ban ghi trong danh sach du lieu

    public Paging(int nrpp, int index, int size) {
        this.nrpp = nrpp;
        this.index = index;
        this.size = size;
    }

    private int totalPage; // tong so trang co the dua tren so luong dong va so dong tren moi trang
    private int begin; // chi so cua dong dau tien tren trang hien tai
    private int end; // chi so cua dong cuoi cung tren trang hien tai
    private int pageStart; //   chi so cua trang dau tien trong day trang duoc thuc thi
    private int pageEnd; //  chi so cua trang cuoi cung trong day trang duoc thuc thi

    public void calc() { // tinh toan de dua ra trang hop li
//		totalPage = size / nrpp + size % nrpp == 0 ? 0 : 1;
        totalPage = (size + nrpp - 1) / nrpp;
        index = index < 0 ? 0 : index;
        index = index >= totalPage ? totalPage - 1 : index;
        begin = index * nrpp;
        end = begin + nrpp;
        end = end >= size ? size : end;
        pageStart = index - 2;
        pageStart = pageStart < 0 ? 0 : pageStart;
        pageEnd = index + 2;
        pageEnd = pageEnd >= totalPage - 1 ? totalPage - 1 : pageEnd;

    }

    public int getNrpp() {
        return nrpp;
    }

    public void setNrpp(int nrpp) {
        this.nrpp = nrpp;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
}
