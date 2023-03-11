package com.Sapient.WeatherApp.data;

import com.Sapient.WeatherApp.models.HeapNode;

import java.time.LocalDateTime;

class Heap {
    private final static Integer MAX = 10000;
    private Integer last;
    private HeapNode[] data;

    public Heap() {
        this.last = 0;
        this.data = new HeapNode[MAX];
    }

    private void reOrderFromTop(final Integer index){
        final Integer left = index*2;
        final Integer right = index*2 + 1;
        Integer minNode = index;

        if(left <= last && data[minNode].timeStamp.isAfter(data[left].timeStamp)){
            minNode = left;
        }

        if(right <= last && data[minNode].timeStamp.isAfter(data[right].timeStamp)){
            minNode = right;
        }

        if(minNode == index){
            return;
        }

        HeapNode curr = data[index];
        data[index] = data[minNode];
        data[minNode] = curr;

        reOrderFromTop(minNode);
    }


    private void removeTop(){
        if(this.last == 0){
            return;
        } else if(this.last == 1) {
            this.data[this.last] = null;
            this.last = 0;
        } else {
            this.data[1] = this.data[last];
            this.data[last] = null;
            last = last - 1;
            reOrderFromTop(1);
        }
    }

    private void cleanUp(final LocalDateTime now){
        while(this.last>0 && this.data[1].timeStamp.plusHours(1).isBefore(now)){
            removeTop();
        }
    }

    private void reOrderFromBottom(final Integer index){
        if(index <= 1){
            return;
        }
        final Integer parentIndex = index/2;
        if(this.data[index].timeStamp.isBefore(this.data[parentIndex].timeStamp)){
            final HeapNode curr = this.data[index];
            this.data[index] = this.data[parentIndex];
            this.data[parentIndex] = curr;
            reOrderFromBottom(parentIndex);
        }
    }

    public void addNew(final String location, final LocalDateTime now){ 
        if(this.last+1<MAX){
            cleanUp(now);
            this.last = this.last + 1;
            this.data[last] = new HeapNode(now, location);
            reOrderFromBottom(last);
        }
    }

    public HeapNode getTop(){
        if(this.last>0){
            return this.data[1];
        }
        return null;
    }
}