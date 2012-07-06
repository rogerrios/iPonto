		function UR_Start() {
			UR_Nu = new Date;
			UR_Indhold = showFilled(UR_Nu.getHours()) + ":"
					+ showFilled(UR_Nu.getMinutes()) + ":"
					+ showFilled(UR_Nu.getSeconds());
			document.getElementById("ur").innerHTML = UR_Indhold;
			setTimeout("UR_Start()", 1000);
		}
		
		function showFilled(Value) {
			return (Value > 9) ? "" + Value : "0" + Value;
		}
		
		function dataCompleta(){
			mydate = new Date();
			myday = mydate.getDay();
			mymonth = mydate.getMonth();
			myweekday= mydate.getDate();
			weekday= myweekday;
			year = mydate.getFullYear();

			if(myday == 0)
			day = " Domingo, ";
			else if(myday == 1)
			day = " Segunda, ";
			else if(myday == 2)
			day = " Terça, ";
			else if(myday == 3)
			day = " Quarta, ";
			else if(myday == 4)
			day = " Quinta, ";
			else if(myday == 5)
			day = " Sexta, ";
			else if(myday == 6)
			day = " Sábado, ";

			if(mymonth == 0)
			month = "Janeiro ";
			else if(mymonth ==1)
			month = "Fevereiro ";
			else if(mymonth ==2)
			month = "Março ";
			else if(mymonth ==3)
			month = "Abril ";
			else if(mymonth ==4)
			month = "Maio ";
			else if(mymonth ==5)
			month = "Junho ";
			else if(mymonth ==6)
			month = "Julho ";
			else if(mymonth ==7)
			month = "Agosto ";
			else if(mymonth ==8)
			month = "Setembro ";
			else if(mymonth ==9)
			month = "Outubro ";
			else if(mymonth ==10)
			month = "Novembro ";
			else if(mymonth ==11)
			month = "Dezembro ";
			document.getElementById("campoData").innerHTML=day+weekday+" de "+month+ " de "+year;
			}
		
