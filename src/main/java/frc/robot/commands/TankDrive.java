package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.XRPDrivetrain;

import java.util.function.Supplier;

public class TankDrive extends Command {
    private final XRPDrivetrain drivetrain;
    private final Supplier<Double> leftSpeedSupplier;
    private final Supplier<Double> rightSpeedSupplier;

    public TankDrive(XRPDrivetrain drivetrain, Supplier<Double> leftSpeedSupplier, Supplier<Double> rightSpeedSupplier) {
        this.drivetrain = drivetrain;
        this.leftSpeedSupplier = leftSpeedSupplier;
        this.rightSpeedSupplier = rightSpeedSupplier;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        drivetrain.tankDrive(leftSpeedSupplier.get(), rightSpeedSupplier.get());
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return false;
    }
}