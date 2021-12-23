package map;

public class veiwMap {
	
	private int tabAmount = 7;
	
	private shipLairCtrl A = shipLairCtrl.getInstance();
    	

	public String toString() { 
		
		
		return ('\n'+
				String.format("%"+(tabAmount+23)+"s", "")+                       				          	        			A.getLrStr(1, 0)+A.getSpStr(1, 0)+A.getLrStr(2, 0)+'\n'+
				String.format("%"+(tabAmount+23)+"s", "")+                                        				  				A.getLrStr(1, 1)+ "            "+A.getLrStr(2, 1)+'\n'+
				String.format("%"+(tabAmount+22)+"s", "")+                                                                     A.getSpStr(2, 0)+"                "+A.getSpStr(3, 0)+'\n'+
				String.format("%"+(tabAmount+21)+"s", "")+                                                                    A.getSpStr(2, 1)+"                  "+A.getSpStr(3, 1)+'\n'+
				String.format("%"+(tabAmount+20)+"s", "")+                                                                   A.getSpStr(2, 2)+"                    "+A.getSpStr(3, 2)+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+                			         " _____________"+              A.getLrStr(3, 0)+ "                      "+A.getLrStr(4, 0)+"_____________ "+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+               				    "/"+"             "+           A.getLrStr(3, 1)+"                      "+A.getLrStr(4, 1)+                 "             "  +"\\"+			     	   "            _______===___"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+                                 "/"+"                "+           A.getSpStr(4, 0)+"                    "+A.getSpStr(5, 0)+                "                "+"\\"+               "             |         |"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+                                "/"+"                  "+           A.getSpStr(4, 1)+"                  "+A.getSpStr(5, 1)+                "                  "+"\\"+               "            |         |"+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+                               "/"+"                    "+           A.getSpStr(4, 2)+"                "+A.getSpStr(5, 2)+                "                    "+"\\"+               "         __|_________|"+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+                              "/"+"                      "+              A.getLrStr(5, 0)+A.getSpStr(6, 0)+A.getLrStr(6, 0)+"                      "+"\\"+               "               ===   "+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+                             "\\"+"                      "+              A.getLrStr(5, 1)+"            "             +A.getLrStr(6, 1)+ "                      "+"/"+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+                              "\\"+"                    "+               A.getSpStr(7, 0)+"                "+A.getSpStr(8, 0)+               "                    "+"/"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+                               "\\"+"                  "+               A.getSpStr(7, 1)+"                  "+A.getSpStr(8, 1)+               "                  "+"/"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+                                "\\"+"                "+               A.getSpStr(7, 2)+"                    "+A.getSpStr(8, 2)+               "                "+"/"+                 		 "            .-----------."+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+                                 A.getLrStr(7, 0)+A.getSpStr(9, 0)+A.getLrStr(8, 0)+  "                      "+ 			A.getLrStr(9, 0)+A.getSpStr(10, 0)+			A.getLrStr(10, 0)+					"           []|           |"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+                                 A.getLrStr(7, 1)+"            "+A.getLrStr(8, 1)+    "                      "+ 			A.getLrStr(9, 1)+"            "+			A.getLrStr(10, 1)+				 	 "           []|           |"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+                  A.getSpStr(11, 0)+"                "+                A.getSpStr(12, 0)+"                    "+A.getSpStr(13, 0)+                "                "+A.getSpStr(14, 0)+        "          []|           |"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+                 A.getSpStr(11, 1)+"                  "+                A.getSpStr(12, 1)+"                  "+A.getSpStr(13, 1)+                "                  "+A.getSpStr(14, 1)+               "           '...........'"+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+                A.getSpStr(11, 2)+"                    "+                A.getSpStr(12, 2)+"                "+A.getSpStr(13, 2)+                "                    "+A.getSpStr(14, 2)+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+               A.getLrStr(11,0)+"                     "+ 					A.getLrStr(12,0)+A.getSpStr(15, 0)+A.getLrStr(13,0)+"                     "+				A.getLrStr(14,0)+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+               A.getLrStr(11,1)+"                     "+ 					A.getLrStr(12,1)+"            "+A.getLrStr(13,1)+"                     "+				A.getLrStr(14,1)+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+                A.getSpStr(16, 0)+"                    "+                 A.getSpStr(17, 0)+"                "+A.getSpStr(18, 0)+               "                    "+A.getSpStr(19, 0)+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+                 A.getSpStr(16, 1)+"                  "+                 A.getSpStr(17, 1)+"                  "+A.getSpStr(18, 1)+               "                  "+A.getSpStr(19, 1)+              "            _===========_"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+                  A.getSpStr(16, 2)+"                "+                 A.getSpStr(17, 2)+"                    "+A.getSpStr(18, 2)+               "                "+A.getSpStr(19, 2)+              "           лл|           |"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+   				A.getLrStr(15,0)+A.getSpStr(20, 0)+					A.getLrStr(16,0)+ "                      "+ A.getLrStr(17,0)+A.getSpStr(21, 0)+			A.getLrStr(18,0)+"            лл|           |"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+   				A.getLrStr(15,1)+"            "+					A.getLrStr(16,1)+ "                      "+ A.getLrStr(17,1)+"            "+			A.getLrStr(18,1)+"            лл|           |"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+                A.getSpStr(22, 0)+"                "+                 A.getSpStr(23, 0)+"                    "+A.getSpStr(24, 0)+               "                "+A.getSpStr(25, 0)+              "             п===========п"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+               A.getSpStr(22, 1)+"                  "+                 A.getSpStr(23, 1)+"                  "+A.getSpStr(24, 1)+               "                  "+A.getSpStr(25, 1)+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+              A.getSpStr(22, 2)+"                    "+                 A.getSpStr(23, 2)+"                "+A.getSpStr(24, 2)+               "                    "+A.getSpStr(25, 2)+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+				A.getLrStr(19,0)+"                     "+ 					A.getLrStr(20,0)+A.getSpStr(26, 0)+A.getLrStr(21,0)+"                     "+				A.getLrStr(22,0)+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+				A.getLrStr(19,1)+"                     "+ 					A.getLrStr(20,1)+"            "+A.getLrStr(21,1)+"                     "+				A.getLrStr(22,1)+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+            A.getSpStr(27, 0)+"                    "+                  A.getSpStr(28, 0)+"                "+A.getSpStr(29, 0)+               "                    "+A.getSpStr(30, 0)+               "           .-----------."+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+             A.getSpStr(27, 1)+"                  "+                  A.getSpStr(28, 1)+"                  "+A.getSpStr(29, 1)+               "                  "+A.getSpStr(30, 1)+               "          []|           |"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+              A.getSpStr(27, 2)+"                "+                  A.getSpStr(28, 2)+"                    "+A.getSpStr(29, 2)+               "                "+A.getSpStr(30, 2)+               "           []|           |"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+   			A.getLrStr(23,0)+A.getSpStr(31, 0)+					A.getLrStr(24,0)+ "                      "+ A.getLrStr(25,0)+A.getSpStr(32, 0)+A.getLrStr(26,0)+"            []|           |"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+  			 A.getLrStr(23,1)+"            "+					A.getLrStr(24,1)+ "                      "+ A.getLrStr(25,1)+"            "+A.getLrStr(26,1)+"              '...........'"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+               "/"+"                "+                               A.getSpStr(33, 0)+"                    "+A.getSpStr(34, 0)+               "                "+"\\"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+              "/"+"                  "+  				                A.getSpStr(33, 1)+"                  "+A.getSpStr(34, 1)+               "                  "+"\\"+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+             "/"+"                    "+   			                 A.getSpStr(33, 2)+"                "+A.getSpStr(34, 2)+               "                    "+"\\"+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+            "/"+"                      "+ 								A.getLrStr(27,0)+   A.getSpStr(35, 0)+A.getLrStr(28,0)+"                      "+"\\"+              "          _______===___"+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+            "\\"+"                      "+								A.getLrStr(27,1)+   "            "+A.getLrStr(28,1) +"                      "+"/"+              "            |         |"+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+             "\\"+"                    "+                  A.getSpStr(36, 0)+"                "+A.getSpStr(37, 0)+               "                    "+"/"+              "             |         |"+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+              "\\"+"                  "+                  A.getSpStr(36, 1)+"                  "+A.getSpStr(37, 1)+               "                  "+"/"+              "            __|_________|"+'\n'+
				String.format("%"+(tabAmount+03)+"s", "")+               "\\"+"                "+                  A.getSpStr(36, 2)+"                    "+A.getSpStr(37, 2)+               "                "+"/"+              "                    ===   "+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+                "\\"+"_____________"+					A.getLrStr(29,0)+  "                      "+ 			A.getLrStr(30,0)+ "_____________/"+'\n'+
				String.format("%"+(tabAmount+04)+"s", "")+                 "              "+					A.getLrStr(29,1)+  "                      "+ 				A.getLrStr(30,1)+"\n"+
				String.format("%"+(tabAmount+03)+"s", "")+                                 "                 "+A.getSpStr(38, 0)+"                    "+A.getSpStr(39, 0)+'\n'+
				String.format("%"+(tabAmount+02)+"s", "")+                                "                   "+A.getSpStr(38, 1)+"                  "+A.getSpStr(39, 1)+'\n'+
				String.format("%"+(tabAmount+01)+"s", "")+                               "                     "+A.getSpStr(38, 2)+"                "+A.getSpStr(39, 2)+'\n'+
				String.format("%"+(tabAmount+00)+"s", "")+               "                       "+ 				A.getLrStr(31,0)+A.getSpStr(40, 0)+A.getLrStr(32,0)+'\n'+
				String.format("%"+(tabAmount+23)+"s", "")+                                          				A.getLrStr(31,0)+"            "+A.getLrStr(32,0)+'\n');
	}

}
