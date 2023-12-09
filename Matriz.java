import java.util.Random;

class Matriz{
	private int[][] mat;
	private int tamLinha;
	private int tamColuna;

	Matriz(){
		mat = new int[6][6];
		this.setTamanhoLinha(6);	
		this.setTamanhoColuna(6);
	}

	Matriz(int numLinhas, int numColunas){
		mat = new int[numLinhas][numColunas];
		this.setTamanhoLinha(numLinhas);	
		this.setTamanhoColuna(numColunas);
	}


	public int getValor(int indiceI,int indiceJ){
		return mat[indiceI][indiceJ];
	}	
	
	public void setValor(int indiceI,int indiceJ, int novoValor){
		mat[indiceI][indiceJ] = novoValor;
	}

	public int getTamanhoLinha(){
		return tamLinha;
	}	
	
	public int getTamanhoColuna(){
		return tamColuna;
	}	

	private void setTamanhoLinha(int novoValor){
		tamLinha = novoValor;
	}

	private void setTamanhoColuna(int novoValor){
		tamColuna = novoValor;
	}

	public void imprime(){
		int conti,contj;	
		for(conti = 0; conti < this.getTamanhoLinha(); conti++){
			System.out.println();
			for(contj = 0; contj < this.getTamanhoColuna(); contj++){
				System.out.print(this.getValor(conti,contj) + " ");
			}
		}
		System.out.println();	
	}

	public void inicializaRandomico(){
		int conti,contj, novoValor;
		int ordem = this.getTamanhoLinha();
		Random gerador = new Random();
		for(conti = 0; conti < this.getTamanhoLinha(); conti++){
			for(contj = 0; contj < this.getTamanhoColuna(); contj++){
				novoValor = gerador.nextInt(ordem);
				this.setValor(conti,contj,novoValor);
			}
		}
	}

	// caso matriz nao quadrada, retorna -1
	public int retorneOrdem(){
		int numL, numC, ordem;

		numL = this.getTamanhoLinha();
		numC = this.getTamanhoColuna();
		ordem = -1;
		if(numL == numC){
			ordem = numL;
		}

		return ordem;
	}	

	private int detOrdem1(Matriz mat){
		return mat.getValor(0,0);
	}
	
	private int detOrdem2(Matriz mat){
		int diagonalP, diagonalI;

		diagonalP = mat.getValor(0,0) * mat.getValor(1,1);		
		diagonalI = mat.getValor(1,0) * mat.getValor(0,1);		

		return (diagonalP - diagonalI);
	}

	private int calculaSinal(int indiceL, int indiceC){
		int sinal;

		sinal = -1;

		if( ((indiceL + indiceC)% 2) == 0 ){   // cof = (-1) elevado a i+j
			sinal = 1;
		}

		return sinal;		
	}

	public void copiaMatrizMaiorParaMenor(Matriz maior,Matriz menor,int isqn,int jsqn){
		int contAi,contAj,contBi,contBj,temp,numL,numC;
		numL = menor.getTamanhoLinha();
		numC = menor.getTamanhoColuna();

		contAi = 0;
		for(contBi = 0; contBi < numL; contBi++){
			if(contAi == isqn){
				contAi++;
			}
			contAj = 0;
			for(contBj = 0; contBj < numC; contBj++){
				if(contAj == jsqn){
					contAj++;
				}
				temp = maior.getValor(contAi,contAj);
				menor.setValor(contBi,contBj,temp);
				contAj++;
			}
			contAi++;
		}
	}

	public int linhaComMaisZeros(){
		int conti, contj, linha, zerosLinha, contaZerosTemp;

		linha = 0;
		zerosLinha = 0;
		//conta zeros da linha;
		for(conti = 0; conti < this.getTamanhoLinha(); conti++){
			contaZerosTemp = 0;
			for(contj = 0; contj < this.getTamanhoColuna(); contj++){
				if(this.getValor(conti,contj) == 0){
					contaZerosTemp += 1;
				}
			}
			if(contaZerosTemp > zerosLinha){
				linha = conti;
				zerosLinha = contaZerosTemp;
			}
		}
		return (linha);
	}

	public int colunaComMaisZeros(){
		int conti, contj, coluna, zerosColuna, contaZerosTemp;
		coluna = 0;
		zerosColuna = 0;
		//conta zeros da coluna;
		for(contj = 0; contj < this.getTamanhoColuna(); contj++){
			contaZerosTemp = 0;
			for(conti = 0; conti < this.getTamanhoLinha(); conti++){
				if(this.getValor(conti,contj) == 0){
					contaZerosTemp += 1;
				}
			}
			if(contaZerosTemp > zerosColuna){
				coluna = contj;
				zerosColuna = contaZerosTemp;
			}
		}
		return (coluna);
	}

	public void andaAsduaslinhas(){
		int conti,contj, contAj;	
		for(conti = 0; conti < this.getTamanhoLinha(); conti++){
			System.out.println();
			for(contj = conti+1; contj < this.getTamanhoColuna(); contj++){
				for(contAj = 0; contAj < this.getTamanhoColuna(); contAj++){
					System.out.print(this.getValor(conti,contAj) + " ");
					System.out.println(this.getValor(contj,contAj) + " ");

				}
			}
		}
		System.out.println();	
	}

	public boolean ehProporcionalLinha(){
		int conti, contj, contAj, proporcao, maior, menor;
		boolean temProporcional, temProporcionalTemp, temZeroL1, temZeroL2, achouProp;


		temProporcional = false;
		for(conti = 0; conti < this.getTamanhoLinha(); conti++){ // percorre a linha base;
			temZeroL1 = false; 
			contj = conti+1;
			while(contj < this.getTamanhoLinha() && temZeroL1 == false && temProporcional == false){ // percorre as outras linhas;
				proporcao = 1;
				contAj = 0;
				achouProp = false;
				temProporcionalTemp = true;
				temZeroL2 = false;
				while(contAj < this.getTamanhoColuna() && temZeroL2 == false && temProporcionalTemp){ // percorre as colunas;
					maior = 2;
					menor = 3;

					if(this.getValor(conti, contAj) == 0){ // verifica se o elemento eh igual a zero;
						temZeroL2 = true;
					}
					if(this.getValor(contj, contAj) == 0){ // verifica se o elemento eh igual a zero;
						temZeroL1 = true;
					}
					if(this.getValor(conti, contAj) != 0 && this.getValor(contj, contAj) != 0){
						if(this.getValor(conti, contAj) >= this.getValor(contj, contAj)){ // verifica qual elemento eh o maior;
							maior = this.getValor(conti, contAj);
							menor = this.getValor(contj, contAj);
							if(maior % menor == 0 && achouProp == false){ //acha a proporcao das linhas;
								proporcao = maior /  menor;
								achouProp = true;
							}
						}
						else{
							maior = this.getValor(contj, contAj);
							menor = this.getValor(conti, contAj);
							if(maior % menor == 0 && achouProp == false){
								proporcao = maior /  menor;
								achouProp = true;
							}
						}
					}
					if(maior != 0 && menor != 0 && (menor * proporcao) != maior){ // verifica se a proporcao se mantem nos proximos elementos;
						temProporcionalTemp = false;
					}
					contAj++;
				}
				if(temProporcionalTemp){
					temProporcional = true;
				}
				contj++;
			}
		}
		return (temProporcional);
	}

	public boolean ehProporcionalColuna(){
		int conti, contj, contAi, proporcao, maior, menor;
		boolean temProporcional, temProporcionalTemp, temZeroL1, temZeroL2, achouProp;


		temProporcional = false;
		for(contj = 0; contj < this.getTamanhoColuna(); contj++){ // percorre a coluna base;
			temZeroL1 = false; 
			conti = contj+1;
			while(conti < this.getTamanhoColuna() && temZeroL1 == false && temProporcional == false){ // percorre as outras colunas;
				proporcao = 1;
				contAi = 0;
				achouProp = false;
				temProporcionalTemp = true;
				temZeroL2 = false;
				while(contAi < this.getTamanhoLinha() && temZeroL2 == false && temProporcionalTemp){ // percorre as linhas;
				this.getValor(contAi, contj);
				this.getValor(contAi, conti);
					maior = 2;
					menor = 3;

					if(this.getValor(contAi, conti) == 0){ // verifica se o elemento eh igual a zero;
						temZeroL2 = true;
					}
					if(this.getValor(contAi, contj) == 0){ // verifica se o elemento eh igual a zero;
						temZeroL1 = true;
					}
					if(this.getValor(contAi, contj) != 0 && this.getValor(contAi, conti) != 0){
						if(this.getValor(contAi, contj) >= this.getValor(contAi, conti)){ // verifica qual elemento eh o maior;
							maior = this.getValor(contAi, contj);
							menor = this.getValor(contAi, conti);
							if(maior % menor == 0 && achouProp == false){ //acha a proporcao das colunas;
								proporcao = maior /  menor;
								achouProp = true;
							}
						}
						else{
							maior = this.getValor(contAi, conti);
							menor = this.getValor(contAi, contj);
							if(maior % menor == 0 && achouProp == false){
								proporcao = maior /  menor;
								achouProp = true;
							}
						}
					}
					if(maior != 0 && menor != 0 && (menor * proporcao) != maior){ // verifica se a proporcao se mantem nos proximos elementos;
						temProporcionalTemp = false;
					}
					contAi++;
				}
				if(temProporcionalTemp){
					temProporcional = true;
				}
				conti++;
			}
		}
		return (temProporcional);
	}

	private int detOrdemN(Matriz mat){
		int sinal,cofator,detTemp,resposta,contL, contC,numL,numC;
		Matriz matmenor;
		numL = this.getTamanhoLinha();
		numC = this.getTamanhoColuna();
		
		resposta = 0;
		for(contC = 0; contC < mat.getTamanhoColuna(); contC++){
			cofator = mat.getValor(0,contC);
			sinal = this.calculaSinal(0,contC);
			matmenor = new Matriz(numL-1,numC-1);
			this.copiaMatrizMaiorParaMenor(mat,matmenor,0,contC);
			detTemp = matmenor.determinante();
			resposta = resposta + (cofator * sinal * detTemp);
		}
		return (resposta);
	}

	private int detOrdemOtimizadoV1(Matriz mat){
		int sinal, cofator, detTemp, resposta, contL, contC, numL, numC, zerosLinha, zerosColuna, conti, contj;
		Matriz matmenor;

		numL = this.getTamanhoLinha();
		numC = this.getTamanhoColuna();
		resposta = 0;
		zerosLinha = 0;
		zerosColuna = 0;

		for(contj = 0; contj < this.getTamanhoLinha(); contj++){
			if(this.getValor(this.linhaComMaisZeros(), contj) == 0){
				zerosLinha += 1;
			}
		}
		for(conti = 0; conti < this.getTamanhoColuna(); conti++){
			if(this.getValor(conti, this.colunaComMaisZeros()) == 0){
				zerosColuna += 1;
			}
		}

		//se tiver mais zeros na linha;
		if(zerosLinha >= zerosColuna){
			contL = this.linhaComMaisZeros();
			for(contC = 0; contC < mat.getTamanhoColuna(); contC++){
				cofator = mat.getValor(contL,contC);
				if(cofator != 0){
					sinal = this.calculaSinal(contL,contC);
					matmenor = new Matriz(numL-1,numC-1);
					this.copiaMatrizMaiorParaMenor(mat,matmenor,contL,contC);
					detTemp = matmenor.determinanteOtimizadoV1();
					resposta = resposta + (cofator * sinal * detTemp);
				}
			}
		}
		//se tiver mais zeros na coluna;
		else{
			contC = this.colunaComMaisZeros();
			for(contL = 0; contL < mat.getTamanhoLinha(); contL++){
				cofator = mat.getValor(contL,contC);
				if(cofator != 0){
					sinal = this.calculaSinal(contL,contC);
					matmenor = new Matriz(numL-1,numC-1);
					this.copiaMatrizMaiorParaMenor(mat,matmenor,contL,contC);
					detTemp = matmenor.determinanteOtimizadoV1();
					resposta = resposta + (cofator * sinal * detTemp);
				}
			}
		}
		return (resposta);
	}

	public int detOrdemOtimizadoV2(Matriz mat){
		int zerosLinha, zerosColuna, sinal,cofator,detTemp,resposta,contL, contC,numL,numC, conti, contj;
		Matriz matmenor;


		if(this.ehProporcionalLinha() || this.ehProporcionalColuna()){ // caso haja linhas ou colunas proporcionais;
			resposta = 0;
		}
		else{
			numL = this.getTamanhoLinha();
			numC = this.getTamanhoColuna();
			zerosLinha = 0;
			zerosColuna = 0;
			resposta = 0;

			for(contj = 0; contj < this.getTamanhoLinha(); contj++){
				if(this.getValor(this.linhaComMaisZeros(), contj) == 0){
					zerosLinha += 1;
				}
			}
			for(conti = 0; conti < this.getTamanhoColuna(); conti++){
				if(this.getValor(conti, this.colunaComMaisZeros()) == 0){
					zerosColuna += 1;
				}
			}

			//se tiver mais zeros na linha;
			if(zerosLinha >= zerosColuna){
				contL = this.linhaComMaisZeros();
				for(contC = 0; contC < mat.getTamanhoColuna(); contC++){
					cofator = mat.getValor(contL,contC);
					if(cofator != 0){
						sinal = this.calculaSinal(contL,contC);
						matmenor = new Matriz(numL-1,numC-1);
						this.copiaMatrizMaiorParaMenor(mat,matmenor,contL,contC);
						detTemp = matmenor.determinanteOtimizadoV1();
						resposta = resposta + (cofator * sinal * detTemp);
					}
				}
			}
			//se tiver mais zeros na coluna;
			else{
				contC = this.colunaComMaisZeros();
				for(contL = 0; contL < mat.getTamanhoLinha(); contL++){
					cofator = mat.getValor(contL,contC);
					if(cofator != 0){
						sinal = this.calculaSinal(contL,contC);
						matmenor = new Matriz(numL-1,numC-1);
						this.copiaMatrizMaiorParaMenor(mat,matmenor,contL,contC);
						detTemp = matmenor.determinanteOtimizadoV1();
						resposta = resposta + (cofator * sinal * detTemp);
					}
				}
			}
		}
		return (resposta);
	}
	
	public int determinante(){
		int ordem,det;

		ordem = this.retorneOrdem();
		det = 0;

		if(ordem > 0){
			switch (ordem) {
			    case 1:  det = this.detOrdem1(this);
				     break;
			    case 2:  det = this.detOrdem2(this);;
				     break;
			    default: det = this.detOrdemN(this);;
				     break;
			}
			
		}
		else{
			System.out.println("Matriz nao eh quadrada!! retornando 0");
		}

		return det;
	}

	public int determinanteOtimizadoV1(){
		int ordem,det;

		ordem = this.retorneOrdem();
		det = 0;

		if(ordem > 0){
			switch (ordem) {
			    case 1:  det = this.detOrdem1(this);
				     break;
			    case 2:  det = this.detOrdem2(this);;
				     break;
			    default: det = this.detOrdemOtimizadoV1(this);;
				     break;
			}
			
		}
		else{
			System.out.println("Matriz nao eh quadrada!! retornando 0");
		}

		return det;
	}

	public int determinanteOtimizadoV2(){
		int ordem,det;

		ordem = this.retorneOrdem();
		det = 0;

		if(ordem > 0){
			switch (ordem) {
			    case 1:  det = this.detOrdem1(this);
				     break;
			    case 2:  det = this.detOrdem2(this);;
				     break;
			    default: det = this.detOrdemOtimizadoV2(this);
				     break;
			}
			
		}
		else{
			System.out.println("Matriz nao eh quadrada!! retornando 0");
		}

		return det;
	}

}
