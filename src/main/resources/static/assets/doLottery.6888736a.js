import{R as S,S as w,d as p,F as N,o as a,c as u,a0 as h,u as i,U as E,a3 as r,a as v,a4 as I,a5 as z,l as D,t as c,aq as P,cf as T,r as m,j as y,w as f,n as g,E as U}from"./index.ed1f32ce.js";import{E as $,a as L}from"./el-table-column.8b77a304.js";import{s as V,f as x,e as O}from"./activity.87e1d361.js";import{E as n}from"./index.1dd000a0.js";import"./_initCloneObject.b49d5e75.js";const j=S({header:{type:String,default:""},bodyStyle:{type:w([String,Object,Array]),default:""},bodyClass:String,shadow:{type:String,values:["always","hover","never"],default:"always"}}),J=p({name:"ElCard"}),R=p({...J,props:j,setup(A){const o=N("card");return(s,t)=>(a(),u("div",{class:h([i(o).b(),i(o).is(`${s.shadow}-shadow`)])},[s.$slots.header||s.header?(a(),u("div",{key:0,class:h(i(o).e("header"))},[E(s.$slots,"header",{},()=>[D(c(s.header),1)])],2)):r("v-if",!0),v("div",{class:h([i(o).e("body"),s.bodyClass]),style:I(s.bodyStyle)},[E(s.$slots,"default")],6)],2))}});var q=z(R,[["__file","/home/runner/work/element-plus/element-plus/packages/components/card/src/card.vue"]]);const M=P(q);const G={class:"lottery-container"},H={key:0,class:"no-activity-message"},K=v("p",null,"\u73B0\u5728\u6CA1\u6709\u8FDB\u884C\u4E2D\u7684\u6D3B\u52A8\u54E6~",-1),Q=[K],W={key:1,class:"flex-container"},X={key:0},Y={key:1},Z={key:2},ee={key:3},te={key:4},se=v("h3",null,"\u672C\u671F\u5956\u54C1\u4FE1\u606F",-1),ae={class:"flex-container"},ue=p({name:"doLottery"}),ie=p({...ue,setup(A){const o=T(),s=m(!1),t=m(null),d=m([]),B=()=>{V({}).then(l=>{if(l.data&&l.status===1){if(s.value=!0,t.value=l.data,console.log("selectActivityInfo =====> success"),!t.value)return n.error("\u6D3B\u52A8id\u4E0D\u80FD\u4E3A\u7A7A"),!1;C(t.value.id,o.userId)}else console.log("selectActivityInfo =====> error"),s.value=!1})},C=(l,_)=>{x({activityId:l,userId:_}).then(e=>{console.log("res : "+JSON.stringify(e)),e.data&&e.status===1&&Boolean(e.data.length)?(console.log("fetchUserPrizes =====> success"),d.value=e.data):(console.log("fetchUserPrizes =====> error"),s.value=!1)})};B();const b=()=>{if(o.userId===0)return n.error("\u8BF7\u9009\u62E9\u6240\u5C5E\u7528\u6237"),!1;if(!t.value)return n.error("\u6D3B\u52A8id\u4E0D\u80FD\u4E3A\u7A7A"),!1;const l=t.value.id,_=o.userId;O({activityId:l,userId:_}).then(e=>{e.data&&e.status===1?(console.log("\u62BD\u5956\u6210\u529F\uFF01\u4E2D\u5956\u4FE1\u606F\uFF1A"+JSON.stringify(e.data)),n.success("\u62BD\u5956\u6210\u529F\uFF01\u606D\u559C\u4F60\u62BD\u4E2D\u4E86\u793C\u7269\u3010"+e.data.giftName+"\u3011")):(n.error(e.message),console.log("\u62BD\u5956\u5931\u8D25\uFF01"+e.message))}).catch(e=>{n.error(e),console.error("\u62BD\u5956\u63A5\u53E3\u8C03\u7528\u51FA\u9519\uFF01",e)})};return(l,_)=>{const e=M,F=$,k=L;return a(),u("div",G,[s.value?(a(),u("div",W,[y(e,{class:"activity-card","body-style":{padding:"50px",width:"80%"}},{default:f(()=>[t.value?(a(),u("h2",X,c(t.value.theme),1)):r("",!0),t.value?(a(),u("p",Y,"\u6D3B\u52A8\u5F00\u59CB\u65F6\u95F4: "+c(t.value.startTime),1)):r("",!0),t.value?(a(),u("p",Z,"\u6D3B\u52A8\u7ED3\u675F\u65F6\u95F4: "+c(t.value.endTime),1)):r("",!0),t.value?(a(),u("p",ee,"\u6D3B\u52A8\u8282\u65E5: "+c(t.value.holidayName),1)):r("",!0),t.value?(a(),u("p",te,"\u6D3B\u52A8\u521B\u5EFA\u4EBA: "+c(t.value.createUserName),1)):r("",!0)]),_:1}),d.value.length>0?(a(),g(e,{key:0,class:"prizes-list","body-style":{padding:"50px",width:"70%"}},{default:f(()=>[se,y(k,{data:d.value,style:{width:"100%"}},{default:f(()=>[y(F,{prop:"giftName",label:"\u5956\u54C1\u540D\u79F0"}),y(F,{prop:"winRate",label:"\u4E2D\u5956\u6982\u7387"})]),_:1},8,["data"])]),_:1})):r("",!0)])):(a(),u("div",H,Q)),v("div",ae,[d.value.length>0?(a(),g(i(U),{key:0,type:"primary",onClick:b,class:"lottery-button"},{default:f(()=>[D(" \u5F00\u59CB\u62BD\u5956 ")]),_:1})):r("",!0)])])}}});export{ie as default};
