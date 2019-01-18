(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0d8af72f"],{1169:function(t,o,e){var i=e("2d95");t.exports=Array.isArray||function(t){return"Array"==i(t)}},"11e9":function(t,o,e){var i=e("52a7"),n=e("4630"),a=e("6821"),r=e("6a99"),c=e("69a8"),m=e("c69a"),d=Object.getOwnPropertyDescriptor;o.f=e("9e1e")?d:function(t,o){if(t=a(t),o=r(o,!0),m)try{return d(t,o)}catch(e){}if(c(t,o))return n(!i.f.call(t,o),t[o])}},2621:function(t,o){o.f=Object.getOwnPropertySymbols},"2a6a":function(t,o,e){"use strict";e.r(o);var i=function(){var t=this,o=t.$createElement,e=t._self._c||o;return e("div",[e("Table",{attrs:{loading:t.loading,size:"large",height:"400",columns:t.columns1,data:t.commodityInfo,stripe:""}}),e("div",{staticStyle:{margin:"10px",overflow:"hidden"}},[e("div",{staticStyle:{float:"right"}},[e("Page",{attrs:{total:t.data_total,current:1},on:{"on-change":function(o){t.changePage(o)}}})],1)]),e("Modal",{attrs:{title:"编辑商品信息",width:"600"},on:{"on-ok":t.btn_update},model:{value:t.show_edit,callback:function(o){t.show_edit=o},expression:"show_edit"}},[e("Form",{attrs:{model:t.commodityUpdateForm,"label-position":"left","label-width":100}},[e("FormItem",{attrs:{label:"商品简介"}},[e("Input",{attrs:{placeholder:"请填写商品的简介"},model:{value:t.commodityUpdateForm.commodityName,callback:function(o){t.$set(t.commodityUpdateForm,"commodityName",o)},expression:"commodityUpdateForm.commodityName"}})],1),e("FormItem",{attrs:{label:"商品价格"}},[e("InputNumber",{staticStyle:{width:"100%"},attrs:{max:1e5,min:.01,placeholder:"请输入商品的价格"},model:{value:t.commodityUpdateForm.commodityPrice,callback:function(o){t.$set(t.commodityUpdateForm,"commodityPrice",o)},expression:"commodityUpdateForm.commodityPrice"}})],1),e("FormItem",{attrs:{label:"商品数量"}},[e("InputNumber",{staticStyle:{width:"100%"},attrs:{min:1,placeholder:"请输入商品的数量"},model:{value:t.commodityUpdateForm.commodityCount,callback:function(o){t.$set(t.commodityUpdateForm,"commodityCount",o)},expression:"commodityUpdateForm.commodityCount"}})],1),e("FormItem",{attrs:{label:"商品单位"}},[e("Input",{attrs:{disabled:""},model:{value:t.commodityUpdateForm.commodityCompany.commodityCompanyName,callback:function(o){t.$set(t.commodityUpdateForm.commodityCompany,"commodityCompanyName",o)},expression:"commodityUpdateForm.commodityCompany.commodityCompanyName"}})],1),e("Row",{attrs:{gutter:16}},[e("Col",{attrs:{span:"4"}},[t._v("商品图片")]),e("Col",{attrs:{span:"8"}},[e("Upload",{attrs:{"on-success":t.ImgUpload_update,name:"filename",action:"http://localhost:8888/fileUpload"}},[e("Button",{attrs:{icon:"ios-cloud-upload-outline"}},[t._v("点击更换商品图片")])],1)],1),e("Col",{attrs:{span:"8"}},[e("div",{staticStyle:{"border-radius":"50%",width:"120px",height:"120px"}},[""!=t.commodityUpdateForm.commodityPhoto?e("img",{attrs:{width:"120px",height:"120px",src:t.URL+t.commodityUpdateForm.commodityPhoto,alt:"出现未知错误!"}}):t._e()])]),e("Col",{attrs:{span:"4"}},[t._v(" ")])],1)],1)],1),e("Modal",{attrs:{width:"360"},model:{value:t.del_show,callback:function(o){t.del_show=o},expression:"del_show"}},[e("p",{staticStyle:{color:"#f60","text-align":"center"},attrs:{slot:"header"},slot:"header"},[e("Icon",{attrs:{type:"ios-information-circle"}}),e("span",[t._v("删除提醒？")])],1),e("div",{staticStyle:{"text-align":"center"}},[e("p",[t._v("您清楚,您现在所做的操作吗？")]),e("p",{staticStyle:{color:"red"}},[t._v("本系统没有数据恢复功能哦")]),e("p"),e("p",[t._v("您确定要删除吗？")])]),e("div",{attrs:{slot:"footer"},slot:"footer"},[e("Button",{attrs:{type:"error",size:"large",long:"",loading:t.modal_loading},on:{click:t.del}},[t._v("确定删除")])],1)])],1)},n=[],a=(e("ac4d"),e("8a81"),e("ac6a"),e("cadf"),e("551c"),e("097d"),{data:function(){var t=this;return{URL:"http://localhost:8888/",data_total:0,commodity_id_del:0,del_show:!1,show_edit:!1,modal_loading:!1,loading:!0,columns1:[{title:"商品简介",key:"commodityName",tooltip:!0},{title:"商品图片",key:"commodityPhoto",align:"center",render:function(t,o){return t("img",{style:{width:"100px",height:"80px","border-radius":"5%"},attrs:{src:"http://localhost:8888/"+o.row.commodityPhoto}})}},{title:"商品价格",key:"commodityPrice",align:"center"},{title:"商品数量",key:"commodityCount",align:"center"},{title:"商品单位",key:"commodityCompanyName",align:"center"},{title:"商品状态",key:"commodityStatus",render:function(o,e){return o("i-switch",{props:{size:"large",value:e.row.commodityStatus},scopedSlots:{open:function(){return o("span","上架")},close:function(){return o("span","下架")}},on:{"on-change":function(o){t.changeStatus(e.row.commodityId,o)}}})}},{title:"操作",align:"center",render:function(o,e){return o("div",[o("Button",{props:{type:"primary",size:"small"},style:{marginRight:"5px"},on:{click:function(){t.commodityEdit(e.row)}}},"编辑"),o("Button",{props:{type:"error",size:"small"},on:{click:function(){t.remove(e.row.commodityId)}}},"删除")])}}],commodityInfo:[],commodityUpdateForm:{commodityId:0,stockId:0,commodityCompanyId:1,commodityName:"",commodityPrice:0,commodityCount:0,commodityPhoto:"",commodityCompany:{commodityCompanyName:""}}}},methods:{del:function(){var t=this,o=this;this.commodity_id_del>0&&(this.modal_loading=!0,axios.delete(this.URL+"commodity/"+this.commodity_id_del).then(function(e){console.log(e.data.code),202==e.data.code?(t.commodityInfo=t.commodityInfo.filter(function(t){return t.commodityId!=o.commodity_id_del}),t.del_show=!1,t.$Message.success(e.data.data)):(t.del_show=!1,t.$Message.success(e.data.data))})),this.modal_loading=!1},changeStatus:function(t,o){var e=this;this.loading=!0,axios.put("http://localhost:8888/commodity/"+t+"/"+o).then(function(t){"202"==t.data.code?e.$Message.success("状态修改成功"):alert("出现异常,请联系管理员。"),e.loading=!1})},commodityEdit:function(t){this.commodityUpdateForm.commodityId=t.commodityId,this.commodityUpdateForm.stockId=t.stockId,this.commodityUpdateForm.commodityName=t.commodityName,this.commodityUpdateForm.commodityCompany.commodityCompanyName=t.commodityCompanyName,this.commodityUpdateForm.commodityCount=t.commodityCount,this.commodityUpdateForm.commodityPhoto=t.commodityPhoto,this.commodityUpdateForm.commodityPrice=t.commodityPrice,this.show_edit=!0},btn_update:function(){var t=this;axios.put(this.URL+"commodity",this.commodityUpdateForm,{"Content-Type":"application/json"}).then(function(o){o.data.code,t.$Message.success(o.data.data)}),this.changePage()},ImgUpload_update:function(t,o){200==t.code&&(this.commodityUpdateForm.commodityPhoto="images/"+t.msg)},remove:function(t){this.commodity_id_del=t,this.del_show=!0},changePage:function(t){this.loading=!0,this.dataTemple(t),this.loading=!1},dataTemple:function(t){var o=this;this.commodityInfo=[],axios.get("http://localhost:8888/commodity",{params:{page:t}}).then(function(t){var e=!0,i=!1,n=void 0;try{for(var a,r=t.data.data[Symbol.iterator]();!(e=(a=r.next()).done);e=!0){var c=a.value;o.commodityInfo.push({commodityId:c.commodityId,commodityName:c.commodityName,commodityPhoto:c.commodityPhoto,commodityPrice:c.commodityPrice,commodityCount:c.commodityCount,commodityStatus:c.commodityStatus,stockId:c.stockId,commodityCompanyId:c.commodityCompanyId,commodityCompanyName:c.commodityCompany.commodityCompanyName})}}catch(m){i=!0,n=m}finally{try{e||null==r.return||r.return()}finally{if(i)throw n}}}),axios.get("http://localhost:8888/commodity/total").then(function(t){o.data_total=t.data.data})}},created:function(){this.changePage()}}),r=a,c=e("2877"),m=Object(c["a"])(r,i,n,!1,null,null,null);m.options.__file="commodityInfo.vue";o["default"]=m.exports},"37c8":function(t,o,e){o.f=e("2b4c")},"3a72":function(t,o,e){var i=e("7726"),n=e("8378"),a=e("2d00"),r=e("37c8"),c=e("86cc").f;t.exports=function(t){var o=n.Symbol||(n.Symbol=a?{}:i.Symbol||{});"_"==t.charAt(0)||t in o||c(o,t,{value:r.f(t)})}},"52a7":function(t,o){o.f={}.propertyIsEnumerable},"67ab":function(t,o,e){var i=e("ca5a")("meta"),n=e("d3f4"),a=e("69a8"),r=e("86cc").f,c=0,m=Object.isExtensible||function(){return!0},d=!e("79e5")(function(){return m(Object.preventExtensions({}))}),s=function(t){r(t,i,{value:{i:"O"+ ++c,w:{}}})},l=function(t,o){if(!n(t))return"symbol"==typeof t?t:("string"==typeof t?"S":"P")+t;if(!a(t,i)){if(!m(t))return"F";if(!o)return"E";s(t)}return t[i].i},u=function(t,o){if(!a(t,i)){if(!m(t))return!0;if(!o)return!1;s(t)}return t[i].w},y=function(t){return d&&f.NEED&&m(t)&&!a(t,i)&&s(t),t},f=t.exports={KEY:i,NEED:!1,fastKey:l,getWeak:u,onFreeze:y}},"7bbc":function(t,o,e){var i=e("6821"),n=e("9093").f,a={}.toString,r="object"==typeof window&&window&&Object.getOwnPropertyNames?Object.getOwnPropertyNames(window):[],c=function(t){try{return n(t)}catch(o){return r.slice()}};t.exports.f=function(t){return r&&"[object Window]"==a.call(t)?c(t):n(i(t))}},"8a81":function(t,o,e){"use strict";var i=e("7726"),n=e("69a8"),a=e("9e1e"),r=e("5ca1"),c=e("2aba"),m=e("67ab").KEY,d=e("79e5"),s=e("5537"),l=e("7f20"),u=e("ca5a"),y=e("2b4c"),f=e("37c8"),p=e("3a72"),h=e("d4c0"),g=e("1169"),b=e("cb7c"),v=e("d3f4"),S=e("6821"),w=e("6a99"),_=e("4630"),P=e("2aeb"),C=e("7bbc"),I=e("11e9"),F=e("86cc"),N=e("0d58"),x=I.f,U=F.f,k=C.f,L=i.Symbol,O=i.JSON,T=O&&O.stringify,E="prototype",M=y("_hidden"),j=y("toPrimitive"),R={}.propertyIsEnumerable,D=s("symbol-registry"),$=s("symbols"),A=s("op-symbols"),G=Object[E],V="function"==typeof L,z=i.QObject,J=!z||!z[E]||!z[E].findChild,B=a&&d(function(){return 7!=P(U({},"a",{get:function(){return U(this,"a",{value:7}).a}})).a})?function(t,o,e){var i=x(G,o);i&&delete G[o],U(t,o,e),i&&t!==G&&U(G,o,i)}:U,H=function(t){var o=$[t]=P(L[E]);return o._k=t,o},K=V&&"symbol"==typeof L.iterator?function(t){return"symbol"==typeof t}:function(t){return t instanceof L},W=function(t,o,e){return t===G&&W(A,o,e),b(t),o=w(o,!0),b(e),n($,o)?(e.enumerable?(n(t,M)&&t[M][o]&&(t[M][o]=!1),e=P(e,{enumerable:_(0,!1)})):(n(t,M)||U(t,M,_(1,{})),t[M][o]=!0),B(t,o,e)):U(t,o,e)},Y=function(t,o){b(t);var e,i=h(o=S(o)),n=0,a=i.length;while(a>n)W(t,e=i[n++],o[e]);return t},q=function(t,o){return void 0===o?P(t):Y(P(t),o)},Q=function(t){var o=R.call(this,t=w(t,!0));return!(this===G&&n($,t)&&!n(A,t))&&(!(o||!n(this,t)||!n($,t)||n(this,M)&&this[M][t])||o)},X=function(t,o){if(t=S(t),o=w(o,!0),t!==G||!n($,o)||n(A,o)){var e=x(t,o);return!e||!n($,o)||n(t,M)&&t[M][o]||(e.enumerable=!0),e}},Z=function(t){var o,e=k(S(t)),i=[],a=0;while(e.length>a)n($,o=e[a++])||o==M||o==m||i.push(o);return i},tt=function(t){var o,e=t===G,i=k(e?A:S(t)),a=[],r=0;while(i.length>r)!n($,o=i[r++])||e&&!n(G,o)||a.push($[o]);return a};V||(L=function(){if(this instanceof L)throw TypeError("Symbol is not a constructor!");var t=u(arguments.length>0?arguments[0]:void 0),o=function(e){this===G&&o.call(A,e),n(this,M)&&n(this[M],t)&&(this[M][t]=!1),B(this,t,_(1,e))};return a&&J&&B(G,t,{configurable:!0,set:o}),H(t)},c(L[E],"toString",function(){return this._k}),I.f=X,F.f=W,e("9093").f=C.f=Z,e("52a7").f=Q,e("2621").f=tt,a&&!e("2d00")&&c(G,"propertyIsEnumerable",Q,!0),f.f=function(t){return H(y(t))}),r(r.G+r.W+r.F*!V,{Symbol:L});for(var ot="hasInstance,isConcatSpreadable,iterator,match,replace,search,species,split,toPrimitive,toStringTag,unscopables".split(","),et=0;ot.length>et;)y(ot[et++]);for(var it=N(y.store),nt=0;it.length>nt;)p(it[nt++]);r(r.S+r.F*!V,"Symbol",{for:function(t){return n(D,t+="")?D[t]:D[t]=L(t)},keyFor:function(t){if(!K(t))throw TypeError(t+" is not a symbol!");for(var o in D)if(D[o]===t)return o},useSetter:function(){J=!0},useSimple:function(){J=!1}}),r(r.S+r.F*!V,"Object",{create:q,defineProperty:W,defineProperties:Y,getOwnPropertyDescriptor:X,getOwnPropertyNames:Z,getOwnPropertySymbols:tt}),O&&r(r.S+r.F*(!V||d(function(){var t=L();return"[null]"!=T([t])||"{}"!=T({a:t})||"{}"!=T(Object(t))})),"JSON",{stringify:function(t){var o,e,i=[t],n=1;while(arguments.length>n)i.push(arguments[n++]);if(e=o=i[1],(v(o)||void 0!==t)&&!K(t))return g(o)||(o=function(t,o){if("function"==typeof e&&(o=e.call(this,t,o)),!K(o))return o}),i[1]=o,T.apply(O,i)}}),L[E][j]||e("32e9")(L[E],j,L[E].valueOf),l(L,"Symbol"),l(Math,"Math",!0),l(i.JSON,"JSON",!0)},9093:function(t,o,e){var i=e("ce10"),n=e("e11e").concat("length","prototype");o.f=Object.getOwnPropertyNames||function(t){return i(t,n)}},ac4d:function(t,o,e){e("3a72")("asyncIterator")},ac6a:function(t,o,e){for(var i=e("cadf"),n=e("0d58"),a=e("2aba"),r=e("7726"),c=e("32e9"),m=e("84f2"),d=e("2b4c"),s=d("iterator"),l=d("toStringTag"),u=m.Array,y={CSSRuleList:!0,CSSStyleDeclaration:!1,CSSValueList:!1,ClientRectList:!1,DOMRectList:!1,DOMStringList:!1,DOMTokenList:!0,DataTransferItemList:!1,FileList:!1,HTMLAllCollection:!1,HTMLCollection:!1,HTMLFormElement:!1,HTMLSelectElement:!1,MediaList:!0,MimeTypeArray:!1,NamedNodeMap:!1,NodeList:!0,PaintRequestList:!1,Plugin:!1,PluginArray:!1,SVGLengthList:!1,SVGNumberList:!1,SVGPathSegList:!1,SVGPointList:!1,SVGStringList:!1,SVGTransformList:!1,SourceBufferList:!1,StyleSheetList:!0,TextTrackCueList:!1,TextTrackList:!1,TouchList:!1},f=n(y),p=0;p<f.length;p++){var h,g=f[p],b=y[g],v=r[g],S=v&&v.prototype;if(S&&(S[s]||c(S,s,u),S[l]||c(S,l,g),m[g]=u,b))for(h in i)S[h]||a(S,h,i[h],!0)}},d4c0:function(t,o,e){var i=e("0d58"),n=e("2621"),a=e("52a7");t.exports=function(t){var o=i(t),e=n.f;if(e){var r,c=e(t),m=a.f,d=0;while(c.length>d)m.call(t,r=c[d++])&&o.push(r)}return o}}}]);
//# sourceMappingURL=chunk-0d8af72f.493ac81d.js.map