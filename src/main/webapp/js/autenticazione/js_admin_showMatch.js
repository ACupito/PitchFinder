function showPlayer(name) {
    var xmlHttpReq = new XMLHttpRequest();
    xmlHttpReq.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            data = JSON.parse(this.response);
            var userName = data.userName.toString();
            var userSurname = data.userSurname.toString();

            var nomiPlayer = data.nomi.toString().split(",");
            var cognomiPlayer = data.cognomi.toString().split(",");

            nomiPlayer.push(userName);
            cognomiPlayer.push(userSurname);


            var father = document.getElementById("players-table");

            if(father.hasChildNodes()){
                for(j=0;j<father.childElementCount;j++){
                    father.removeChild(father.lastChild)
                    j--;
                }
            }

            var table = document.createElement("table");
            table.setAttribute("class","responsive-table");
            table.setAttribute("style","width: 90%")

            var thead = document.createElement("thead");
            var tbody = document.createElement("tbody");
            tbody.setAttribute("id","tbody_partite");

            var trHead = document.createElement("tr");
            var th1Head = document.createElement("th");
            th1Head.setAttribute("scope","col");
            var th1Text = document.createTextNode("Nome");
            th1Head.appendChild(th1Text);
            var th2Head = document.createElement("th");
            th2Head.setAttribute("scope","col");
            var th2Text = document.createTextNode("Cognome");
            th2Head.appendChild(th2Text);

            trHead.appendChild(th1Head);
            trHead.appendChild(th2Head);
            thead.appendChild(trHead);

            //Creating row

            for(var i=0; i < nomiPlayer.length; i++) {
                if (nomiPlayer[i].match("^[a-zA-Z\\s]+$")) {
                    var bodyRow = document.createElement("tr");
                    var rowTh = document.createElement("th");
                    rowTh.setAttribute("scope", "row");
                    var rowThText = document.createTextNode(nomiPlayer[i]);
                    rowTh.appendChild(rowThText);

                    var rowTd = document.createElement("td");
                    rowTd.setAttribute("data-title", "Cognome")
                    var rowTdText = document.createTextNode(cognomiPlayer[i]);
                    rowTd.appendChild(rowTdText);

                    bodyRow.appendChild(rowTh);
                    bodyRow.appendChild(rowTd);

                    tbody.appendChild(bodyRow);
                }
            }
            table.appendChild(thead);
            table.appendChild(tbody);
            father.appendChild(table);
        }

    }
    xmlHttpReq.open("GET", "showAj?codice="+encodeURIComponent(name) , true);
    xmlHttpReq.send();
}

function filterMatch(){
    var data;
    if(document.getElementById("filter-date").value == null ||
        !document.getElementById("filter-date").value.match("^(.*[-])[0-9]*$")){
        data = "null";
    }else{
        data = document.getElementById("filter-date").value;
    }

    var time;
    if(document.getElementById("filter-time").value == null ||
        !document.getElementById("filter-time").value.match("^(.*[:])[0-9]*$")){
        time = "null";
    }else{
        time = document.getElementById("filter-time").value;
    }

    alert(data +":"+time);

    $(document).ready(function (){
        $.getJSON("FilterJSON", {data: data,time: time}, function (data){
            alert("we waju bell sta call back");
            myFunction(data);
        });
    });

}

function myFunction(data){
    alert("CIAO");
    alert("prova:"+data[0].idPartita+":prova");
}