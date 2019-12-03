create trigger auditoria on tbServidor
after insert
as
    begin
        declare
		@codServidor int,
        @nomeServidor varchar(100),
		@codUsuario int,
		@nomeUsuario varchar(100)

        select @codServidor = i.codServidor, @nomeServidor = i.loginServidor, @codUsuario = i.codUsuario, @nomeUsuario = u.nomeUsuario from inserted as i
			inner join tbUsuario as u on (u.codUsuario = i.codUsuario)
        insert into tbAuditoria
            values(@codServidor, 'tbServidor' , 'INSERT', 'Link do Servidor | Nome do Servidor', getdate(), @codUsuario, @nomeUsuario)
    end
go


create trigger auditoriaUpdate on tbServidor
after update
as
    begin
        declare
		@codServidor int,
        @nomeServidor varchar(100),
		@codUsuario int,
		@nomeUsuario varchar(100)

        select @codServidor = i.codServidor, @nomeServidor = i.loginServidor, @codUsuario = i.codUsuario, @nomeUsuario = u.nomeUsuario from inserted as i
			inner join tbUsuario as u on (u.codUsuario = i.codUsuario)
        insert into tbAuditoria
            values(@codServidor, 'tbServidor' , 'UPDATE', 'Link do Servidor | Nome do Servidor', getdate(), @codUsuario, @nomeUsuario)
    end
go


create trigger auditoriaDelete on tbServidor
after delete
as
    begin
        declare
		@codServidor int,
        @nomeServidor varchar(100),
		@codUsuario int,
		@nomeUsuario varchar(100)

        select @codServidor = i.codServidor, @nomeServidor = i.loginServidor, @codUsuario = i.codUsuario, @nomeUsuario = u.nomeUsuario from inserted as i
			inner join tbUsuario as u on (u.codUsuario = i.codUsuario)
        insert into tbAuditoria
            values(@codServidor, 'tbServidor' , 'DELETE', 'Link do Servidor | Nome do Servidor', getdate(), @codUsuario, @nomeUsuario)
    end
go


create trigger DisponibilidadeVsIndisponibilidade on tbIndisponibilidade
after
insert
 as 
 begin
 declare
	@ValorDisponivelTotal int,
	@ValorIndisponivel int
		SET @ValorDisponivelTotal = (select count(codIndisponibilidade) from tbIndisponibilidade where MONTH(dataHoraIndisponibilidade) = MONTH(CURRENT_TIMESTAMP));
		SET @ValorIndisponivel = (select count(codIndisponibilidade) from tbIndisponibilidade where disponivel = 0 AND MONTH(dataHoraIndisponibilidade) = MONTH(CURRENT_TIMESTAMP));
		UPDATE tbServidor
		set disponivelMes = @ValorDisponivelTotal - @ValorIndisponivel
		where codServidor = 1
		UPDATE tbServidor
		set indisponivelMes = @ValorIndisponivel
		where codServidor = 1
	end
	go

	select * from tbServidor;

alter trigger AtualizarStatusServidor on tbServidor
after
update
as
	if((select disponivel from tbIndisponibilidade where codServidor = 1 and codIndisponibilidade = 
			(select top 1 codIndisponibilidade from tbIndisponibilidade order by codIndisponibilidade desc)) = 0)
		UPDATE tbServidor	
		SET statusServidor = 'offline'
		where codServidor = 1
	else
		UPDATE tbServidor	
		SET statusServidor = 'online'
		where codServidor = 1

		select disponivel from tbIndisponibilidade where codServidor = 1 and codIndisponibilidade = 
			(select top 1 codIndisponibilidade from tbIndisponibilidade order by codIndisponibilidade desc)

			select * from tbServidor