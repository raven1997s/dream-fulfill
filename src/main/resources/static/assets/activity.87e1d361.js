import{cd as a,ce as s}from"./index.ed1f32ce.js";const r=async t=>(await a({url:s+"/activity/pageQueryActivityList",method:"post",data:t})).data,c=async t=>(await a({url:s+"/activity/updateActivity",method:"post",data:t})).data,o=async t=>(await a({url:s+"/activity/addActivity",method:"post",data:t})).data,d=async t=>(await a({url:s+"/activity/deleteActivity",method:"post",data:t})).data,u=async t=>(await a({url:s+"/activity/stat/pageQueryActivityStatList",method:"post",data:t})).data,n=async t=>(await a({url:s+"/drawRecord/pageQueryDrawRecordList",method:"post",data:t})).data,y=async t=>(await a({url:s+"/activity/selectActivityInfo",method:"post",data:t})).data,l=async t=>(await a({url:s+"/activity/stat/findCurrentActivityStatList",method:"post",data:t})).data,v=async t=>(await a({url:s+"/activity/doLottery",method:"post",data:t})).data;export{o as a,u as b,n as c,d,v as e,l as f,r as g,y as s,c as u};
