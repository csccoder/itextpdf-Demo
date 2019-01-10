package dataSource;

import config.BaseModuleConfig;
import config.StartingBaseModuleConfig;
import config.StopBaseModuleConfig;
import vo.SccQrqcStartingVO;
import vo.SccQrqcStopVO;

import java.util.*;

public class Datasource {
    public static BaseModuleConfig getStartingModuleConfig(){
        return StartingBaseModuleConfig.getInstance();
    }

    public static BaseModuleConfig getStopModuleConfig(){
        return StopBaseModuleConfig.getInstance();
    }

    public static List<SccQrqcStartingVO> getStartingDataList(){
        ArrayList<SccQrqcStartingVO> vos = new ArrayList<SccQrqcStartingVO>();
        SccQrqcStartingVO vo = new SccQrqcStartingVO();
        vo.setOrderName("11-1002-dsd4-49-01-000");
        vo.setQuestionType("来料异常");
        vo.setQuestionDesc("27号测试");
        vo.setInfluence(1);
        vo.setBadNum(1);
        vo.setUnitPosition("Q5002");
        vo.setHasStop("N");
        vo.setSite("");
        vo.setLineName("SMT4-1");
        vo.setPosition("AVI检验");

        vos.add(vo);
        return vos;
    }

    public static List<SccQrqcStopVO> getStopDataList(){
        List vos = new ArrayList<SccQrqcStopVO>();
        SccQrqcStopVO vo = new SccQrqcStopVO();
        vo.setWorkcell("0");
        vo.setStop("0");
        vo.setProducer("小红");
        vo.setOutuser("小明");
        vo.setCreationDate(new Date());
        vo.setOperator("小赵");

        vos.add(vo);
        return vos;
    }

    public static Map<String,List> getDataMap(){
        Map<String, List> map = new HashMap<String, List>();
        map.put("starting",getStartingDataList());
        map.put("stop",getStopDataList());
        return map;
    }


    public static Map<String, BaseModuleConfig> getModuleConfigMap(){
        Map<String, BaseModuleConfig> map = new LinkedHashMap<String, BaseModuleConfig>();
        map.put("starting",getStartingModuleConfig());
        map.put("stop",getStopModuleConfig());
        return map;
    }
}
