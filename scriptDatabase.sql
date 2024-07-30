

CREATE TABLE funcionarios (
  id INTEGER PRIMARY KEY,
  nome TEXT NOT NULL,
  matricula INTEGER NOT NULL UNIQUE,
  setor TEXT NOT NULL,
  turno TEXT NOT NULL,
  funcao TEXT NOT NULL,
  data_admissao TEXT NOT NULL,
  escala TEXT NOT NULL,
  horario TEXT NOT NULL,
  horas_semanais TEXT NOT NULL,
  codigo_de_barras TEXT NOT NULL,
  senha TEXT NOT NULL
);

CREATE TABLE registros (
  id INTEGER PRIMARY KEY ,
  setor TEXT NOT NULL,
  turno TEXT NOT NULL,
  funcao TEXT NOT NULL,
  id_funcionario INTEGER NOT NULL,
  hora_entrada TEXT NOT NULL,
  saida_almoco TEXT NOT NULL,
  retorno_almoco TEXT NOT NULL,
  saida TEXT NOT NULL,
  data TEXT NOT NULL,
  FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id)
);


CREATE TABLE assinatura (
  id INTEGER PRIMARY KEY ,
  id_funcionario INTEGER NOT NULL,
  data_assinatura TEXT NOT NULL,
  hora_assinatura TEXT NOT NULL,
  FOREIGN KEY (id_funcionario) REFERENCES funcionarios(id) 
);



DROP TABLE IF EXISTS sqlite_sequence;

