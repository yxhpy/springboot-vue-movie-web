import vue from "vue"

export let BASEURL = "http://cwcc.cf/"
export let loginForm = (data) => vue.axios.post("movie/admin/login", data);
export let getMovie = (data) => vue.axios.get("movie/movie/get?" + data);
export let addMovie = (data) => vue.axios.post("movie/movie//add", data);
export let updateMovie = (data) => vue.axios.post("movie/movie//update", data);
export let deleteMovie = (data) => vue.axios.delete("movie/movie//delete?" + data);
export let getLocalTop5 = () => vue.axios.get("movie/movie/get/local/count/top5");
export let getType = () => vue.axios.get("movie/movie/get/type");
export let getCountAll = () => vue.axios.get("movie/movie/get/count/all");
export let getDetails = (mid) => vue.axios.get("movie/movie/get/" + mid);
export let getTypes = () => vue.axios.get("movie/m-type/get/all");
export let getMovieSources = (mid) => vue.axios.get("movie/play-sources/get?mid=" + mid);

