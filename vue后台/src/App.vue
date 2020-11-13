<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
// import LoginVue from './views/Login.vue'

export default {
  name: 'app',
  components: {
  } 
}
</script>

<style>
html, body, div, span, applet, object, iframe, h1, h2, h3, h4, h5, h6, p, blockquote, pre, a, abbr, acronym, address, big, cite, code, del, dfn, em, font, img, ins, kbd, q, s, samp, small, strike, 
strong, sub, sup, tt, var, b, u, i, center, dl, dt, dd, ol, ul, li, fieldset, form, label, legend, table, caption, tbody, 
tfoot, thead, tr, th, td ,textarea,input { margin:0; padding:0;  }
address,cite,dfn,em,var, i {font-style:normal;}
body {font-size: 16px; line-height: 1.5; font-family:'Microsoft Yahei','simsun','arial','tahoma';  color: #333; }
table { border-collapse:collapse; border-spacing:0; }
h1, h2, h3, h4, h5, h6, th { font-size: 100%; font-weight: normal; }
button,input,select,textarea{font-size:100%;}
fieldset,img{border:0;}
a,
img {
    -webkit-touch-callout: none
}
a,
a:active,
a:focus,
a:hover,
a:visited {text-decoration: none}
input[type=password],
input[type=text],
textarea {
    resize: none;
    outline: 0;
    -webkit-appearance: none;
    white-space: pre-wrap;
    word-wrap: break-word;
    background: #fff
}
ul, ol { list-style: none; }
:focus{ outline:none;}
.clearfix{ clear: both; content: ""; display: block; overflow: hidden }
.clear{clear: both;}
.fl{ float: left; }
.fr{float: right;}
</style>
