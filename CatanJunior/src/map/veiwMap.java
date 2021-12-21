package map;

public class veiwMap {
	
	private int tabAmount = 7;
	
	private shipLairCtrl A = shipLairCtrl.getInstance();
    	

	public String toString() { 
		
		
		return ('\n'+
				String.format("%"+(tabAmount+23)+"s", "")+                                         A.getLrStr(1, 0)+"____________"+A.getLrStr(2, 0)+'\n'+
				String.format("%"+(tabAmount+23)+"s", "")+                                          A.getLrStr(1, 0)+"            "+A.getLrStr(2, 0)+'\n'+
				String.format("%"+(tabAmount+22)+"s", "")+                                                       "/                \\"+'\n'+
				String.format("%"+(tabAmount+21)+"s", "")+                                                      "/                  \\"+'\n'+
				String.format("%"+(tabAmount+20)+"s", "")+                                                     "/                    \\"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+                   " _____________"+A.getLrStr(3, 0)+ "                      "+A.getLrStr(4, 0)+"_____________"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+                   "/             "+A.getLrStr(3, 0)+ "                      "+A.getLrStr(4, 0)+"             \\"+               "            _______===___"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+                  "/                "+              "\\                    /"+                "                \\"+               "             |         |"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+                 "/                  "+              "\\                  /"+                "                  \\"+               "            |         |"+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+                "/                    "+              "\\                /"+                "                    \\"+               "         __|_________|"+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+               "/                      "+ A.getLrStr(5, 0)+"____________"+A.getLrStr(6, 0)+"                      \\"+               "               ===   "+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+               "\\                      "+A.getLrStr(5, 0)+"            "+A.getLrStr(6, 0)+ "                      /"+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+                "\\                    "+               "/                \\"+               "                    /"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+                 "\\                  "+               "/                  \\"+               "                  /"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+                  "\\                "+               "/                    \\"+               "                /"+                 "            .-----------."+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+     A.getLrStr(7, 0)+"____________"+A.getLrStr(8, 0)+ "                      "+ A.getLrStr(9, 0)+"____________"+A.getLrStr(10, 0)+"           []|           |"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+     A.getLrStr(7, 0)+"            "+A.getLrStr(8, 0)+ "                      "+ A.getLrStr(9, 0)+"            "+A.getLrStr(10, 0)+"           []|           |"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+                  "/                "+                "\\                    /"+                "                \\"+               "          []|           |"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+                 "/                  "+                "\\                  /"+                "                  \\"+               "           '...........'"+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+                "/                    "+                "\\                /"+                "                    \\"+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+A.getLrStr(11,0)+"                     "+ A.getLrStr(12,0)+"____________"+A.getLrStr(13,0)+"                     "+A.getLrStr(14,0)+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+A.getLrStr(11,0)+"                     "+ A.getLrStr(12,0)+"            "+A.getLrStr(13,0)+"                     "+A.getLrStr(14,0)+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+              "\\                    "+                 "/                \\"+               "                    /"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+               "\\                  "+                 "/                  \\"+               "                  /"+              "            _===========_"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+                "\\                "+                 "/                    \\"+               "                /"+              "           лл|           |"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+   A.getLrStr(15,0)+"____________"+A.getLrStr(16,0)+ "                      "+ A.getLrStr(17,0)+"____________"+A.getLrStr(18,0)+"            лл|           |"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+   A.getLrStr(15,0)+"            "+A.getLrStr(16,0)+ "                      "+ A.getLrStr(17,0)+"            "+A.getLrStr(18,0)+"            лл|           |"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+                "/                "+                 "\\                    /"+               "                \\"+              "             п===========п"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+               "/                  "+                 "\\                  /"+               "                  \\"+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+              "/                    "+                 "\\                /"+               "                    \\"+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+A.getLrStr(19,0)+"                     "+ A.getLrStr(20,0)+"____________"+A.getLrStr(21,0)+"                     "+A.getLrStr(22,0)+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+A.getLrStr(19,0)+"                     "+ A.getLrStr(20,0)+"            "+A.getLrStr(21,0)+"                     "+A.getLrStr(22,0)+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+            "\\                    "+                  "/                \\"+               "                    /"+               "           .-----------."+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+             "\\                  "+                  "/                  \\"+               "                  /"+               "          []|           |"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+              "\\                "+                  "/                    \\"+               "                /"+               "           []|           |"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+   A.getLrStr(23,0)+"____________"+A.getLrStr(24,0)+ "                      "+ A.getLrStr(25,0)+"____________"+A.getLrStr(26,0)+"            []|           |"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+   A.getLrStr(23,0)+"            "+A.getLrStr(24,0)+ "                      "+ A.getLrStr(25,0)+"            "+A.getLrStr(26,0)+"              '...........'"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+               "/                "+                  "\\                    /"+               "                \\"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+              "/                  "+                  "\\                  /"+               "                  \\"+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+             "/                    "+                  "\\                /"+               "                    \\"+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+            "/                     "+ A.getLrStr(27,0)+   "_____________"+A.getLrStr(28,0)+"                      \\"+              "          _______===___"+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+            "\\                     "+A.getLrStr(27,0)+   "             "+A.getLrStr(28,0) +"                      /"+              "            |         |"+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+             "\\                    "+                  "/                \\"+               "                    /"+              "             |         |"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+              "\\                  "+                  "/                  \\"+               "                  /"+              "            __|_________|"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+               "\\                "+                  "/                    \\"+               "                /"+              "                    ===   "+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+                "\\_____________"+A.getLrStr(29,0)+  "                      "+ A.getLrStr(30,0)+ "_____________/"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+                 "              "+A.getLrStr(29,0)+  "                      "+ A.getLrStr(30,0)+"\n"+
				String.format("%"+(tabAmount+03)+"s", "")+                                 "                 "+"\\                    /"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+                                "                   "+"\\                  /"+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+                               "                     "+"\\                /"+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+               "                       "+ A.getLrStr(31,0)+"____________"+A.getLrStr(32,0)+'\n'+
				String.format("%"+(tabAmount+23)+"s", "")+                                          A.getLrStr(31,0)+"            "+A.getLrStr(32,0)+'\n');
	}

}
