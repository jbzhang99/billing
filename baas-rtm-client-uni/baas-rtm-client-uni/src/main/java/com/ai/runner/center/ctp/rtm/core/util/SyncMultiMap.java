package com.ai.runner.center.ctp.rtm.core.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.map.MultiValueMap;


public class SyncMultiMap {

	private MultiValueMap<String,StringLine> multiMap = new MultiValueMap<String,StringLine>();
	
	synchronized public void put(String k,StringLine v) {
		multiMap.put(k, v);
		System.out.println("current total size="+totalSize());
	}
	
	synchronized public void putAll(String key,Collection<StringLine> values) {
		multiMap.putAll(key, values);
	}
	
	@SuppressWarnings("unchecked")
    synchronized public List<StringLine> remove(Object k) {
        return (List<StringLine>)multiMap.remove(k);
    }
    
    synchronized public int keySize() {
        return multiMap.size();
    }
	
    synchronized public int size(Object k) {
        return multiMap.size(k);
    }
    
    synchronized public int totalSize() {
        return multiMap.totalSize();
    }
    
    //@SuppressWarnings("unchecked")
    synchronized public Set<String> keys() {
        return multiMap.keySet();
    }
    
    synchronized public void clear(){
    	multiMap.clear();
    }
    
    
    /**
     * 从已知的队列中取得充足的元素值
     * @param key
     * @param max
     * @return
     */
    @SuppressWarnings("unchecked")
    synchronized public List<StringLine> getAdequateElement(String key, int max){
    	List<StringLine> rtnQueue = null;
		List<StringLine> queue = (List<StringLine>)multiMap.get(key);
    	if(queue == null){
    		return rtnQueue;
    	}
    	int each = 1;
    	if(max>1){
    		each = queue.size()/max;
    	}
    	if(each<=0){
    		return rtnQueue;
    	}
    	//rtnQueue = queue.subList(0, each*max);
    	rtnQueue = new ArrayList<StringLine>(queue.subList(0, each*max));
    	queue.removeAll(rtnQueue);
    	return rtnQueue;
    }
    
    /**
     * 得到所有队列中超过上限的所有key
     * @param max
     * @return
     */
    synchronized public List<String> getBeyondMaxLimitKeys(int max){
		List<String> rtnKeys = new ArrayList<String>();
		Set<String> ketSets = multiMap.keySet();
		int valueSize = 0;
		for(String key:ketSets){
			valueSize = multiMap.size(key);
			if(valueSize>=max){
				rtnKeys.add(key);
			}
		}
		return rtnKeys;
	}
    
    @SuppressWarnings("unchecked")
    synchronized public SyncMultiMap clone(){
    	SyncMultiMap syncMultiMap = new SyncMultiMap();
    	Entry<String,Object> entry;
    	List<StringLine> msgQueue;
    	for(Iterator<Entry<String,Object>> iter=multiMap.entrySet().iterator();iter.hasNext();){
    		entry = iter.next();
    		msgQueue = (List<StringLine>)entry.getValue();
    		//System.out.println("clone key="+entry.getKey()+",values="+msgQueue.subList(0, msgQueue.size()));
    		if(msgQueue.size()==0){
    			continue;
    		}
    		syncMultiMap.putAll(entry.getKey(), msgQueue.subList(0, msgQueue.size()));
    	}
    	return syncMultiMap;
    }
    
    synchronized public SyncMultiMap cloneAndClear(){
    	SyncMultiMap syncMultiMap = clone();
    	clear();
    	return syncMultiMap;
    }
    
    
    public void printMultiMap(){
    	Entry<String,Object> entry;
    	List<StringLine> msgQueue;
    	for(Iterator<Entry<String,Object>> iter=multiMap.entrySet().iterator();iter.hasNext();){
    		entry = iter.next();
    		msgQueue = (List<StringLine>)entry.getValue();
    		//System.out.println("clone key="+entry.getKey()+",values="+msgQueue.subList(0, msgQueue.size()));
    		for(StringLine line:msgQueue){
    			StringBuilder out = new StringBuilder();
    			out.append("key=").append("[").append(entry.getKey()).append("],");
    			out.append("value=").append(line.toString());
    			System.out.println(out.toString());
    		}
    	}
    }
    
    
    
    public static void main(String[] args) {
    	SyncMultiMap multiMap = new SyncMultiMap();
    	
//    	multiMap.put("test1", "111");
//    	multiMap.put("test1", "2222");
//    	multiMap.put("test1", "33333");
//    	multiMap.put("test3", "a11");
//    	multiMap.put("test5", "t11");
//    	multiMap.put("test5", "t222");
//    	multiMap.put("test5", "t3333");
    	
//    	System.out.println("before values size="+multiMap.keySize());
//    	System.out.println("before total size="+multiMap.totalSize());
//    	List<String> element =  multiMap.remove("test1");
//    	System.out.println("after values size="+multiMap.keySize());
//    	System.out.println("after total size="+multiMap.totalSize());
//    	System.out.println(element);
    	
//    	List<String> queue = multiMap.getList("test2");
//
//    	System.out.println("queue size="+queue.size());
//    	System.out.println("queue content="+queue.toString());
//    	List<String> temp = queue.subList(0, 2);
//    	System.out.println("temp size="+temp.size());
//    	System.out.println("temp content="+temp.toString());
//    	
//    	queue.removeAll(temp);
//    	System.out.println("queue size="+queue.size());
//    	System.out.println("queue content="+queue.toString());
//    	
    	
    	System.out.println(11/3);
    	
    	
    	
	}
	
}
